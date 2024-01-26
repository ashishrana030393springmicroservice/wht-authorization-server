package com.user.auth.entity.client;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import static com.user.auth.Utils.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="clients")
@Entity
public class Client extends BaseId{
   @Column(unique = true)
   private String clientId;
   private String secret;
   @ManyToMany(mappedBy = "clients")
   private Set<AuthenticationMethod> authenticationMethods = new HashSet<>();
   @ManyToMany(mappedBy = "clients")
   private Set<RedirectUrl> redirectUrls = new HashSet<>();
   @ManyToMany(mappedBy = "clients")
   private Set<Scope> scopes = new HashSet<>();
   @ManyToMany(mappedBy = "clients")
   private Set<GrantType> grants = new HashSet<>();
   @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
   private ClientTokenSettings setting;


   public void addMethod(AuthenticationMethod authenticationMethod) {
      authenticationMethod.getClients().add(this);
      this.authenticationMethods.add(authenticationMethod);
   }

   public void addGrant(GrantType grantType) {
      grantType.getClients().add(this);
      grants.add(grantType);
   }

   public void addScope(Scope scope) {
      scope.getClients().add(this);
      scopes.add(scope);
   }

   public void addRedirectUrl(RedirectUrl redirectUrl) {
      redirectUrl.getClients().add(this);
      redirectUrls.add(redirectUrl);
   }

   public RegisteredClient getRegisteredClient(){
      RegisteredClient build = RegisteredClient.withId(Long.toString(getId()))
              .clientId(getClientId())
              .clientSecret(getSecret())
              .clientAuthenticationMethods(methods(getAuthenticationMethods()))
              .scopes(scopes(getScopes()))
              .authorizationGrantTypes(grantTypes(getGrants()))
              .redirectUris(redirectUrls(getRedirectUrls()))
              .tokenSettings(TokenSettings.builder()
                      .accessTokenTimeToLive(Duration.ofHours(getSetting().getAccessTokenTTL()))
                      .accessTokenFormat(OAuth2TokenFormat.REFERENCE)
                      .build())
              .build();
      return build;
   }
}
