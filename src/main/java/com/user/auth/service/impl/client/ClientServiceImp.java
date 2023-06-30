package com.user.auth.service.impl.client;

import com.user.auth.config.AppConfig;
import com.user.auth.dao.ClientRepository;
import com.user.auth.entity.client.*;
import com.user.auth.service.AuthenticationMethodService;
import com.user.auth.service.GrantService;
import com.user.auth.service.RedirectUrlService;
import com.user.auth.service.ScopeService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientServiceImp implements RegisteredClientRepository {
    private final ClientRepository clientRepository;
    private final AuthenticationMethodService authenticationMethodService;
    private final RedirectUrlService redirectUrlService;
    private final GrantService grantService;
    private final ScopeService scopeService;
    private final AppConfig appConfig;

    @PostConstruct
    public void init(){
        RegisteredClient build = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("axkdw-skdsfs-ksdfks-akdfks")
                .clientSecret("jskex-kwics-iecmw-aziec")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .scope(OidcScopes.OPENID)
                .redirectUri( appConfig.getUi1().getUrl() + "/signin-callback")
                .tokenSettings(TokenSettings.builder()
                        .accessTokenFormat(OAuth2TokenFormat.REFERENCE)
                        .accessTokenTimeToLive(Duration.ofHours(6))
                        .build())
                .build();
        RegisteredClient byClientId = findByClientId(build.getClientId());
        if(byClientId ==null){
            save(build);
        }
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        Client client = new Client();
        client.setClientId(registeredClient.getClientId());
        client.setSecret(registeredClient.getClientSecret());
        ClientTokenSettings setting = new ClientTokenSettings();
        setting.setClient(client);
        setting.setAccessTokenTTL(registeredClient.getTokenSettings().getAccessTokenTimeToLive().getSeconds());
        setting.setType(registeredClient.getTokenSettings().getAccessTokenFormat().getValue());
        client.setSetting(setting);
        this.clientRepository.save(client);
        for(ClientAuthenticationMethod method: registeredClient.getClientAuthenticationMethods()){
            AuthenticationMethod authenticationMethod = this.authenticationMethodService.findAuthenticationMethod(method);
            client.addMethod(authenticationMethod);
            this.authenticationMethodService.save(authenticationMethod);
        }
        for(AuthorizationGrantType authorizationGrantType:registeredClient.getAuthorizationGrantTypes()){
            GrantType grantType = this.grantService.findByGrantType(authorizationGrantType);
            client.addGrant(grantType);
            this.grantService.save(grantType);
        }
        for(String oidcScope: registeredClient.getScopes()){
            Scope scope = this.scopeService.findByScope(oidcScope);
            client.addScope(scope);
            this.scopeService.save(scope);

        }
        for(String url: registeredClient.getRedirectUris()){
            RedirectUrl redirectUrl = this.redirectUrlService.findByUrl(url);
            client.addRedirectUrl(redirectUrl);
            this.redirectUrlService.save(redirectUrl);
        }
        this.clientRepository.save(client);
    }

    @Override
    public RegisteredClient findById(String id) {
        return this.clientRepository.findById(Long.parseLong(id)).map(c-> c.getRegisteredClient())
                .orElseThrow(()->new RuntimeException("invalid id"));
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Client byClientId = this.clientRepository.findByClientId(clientId);
        return byClientId==null?null:byClientId.getRegisteredClient();
    }
}
