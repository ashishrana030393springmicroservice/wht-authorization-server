package com.user.auth;

import com.user.auth.entity.client.AuthenticationMethod;
import com.user.auth.entity.client.GrantType;
import com.user.auth.entity.client.RedirectUrl;
import com.user.auth.entity.client.Scope;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;

import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Utils {
    public static Consumer<Set<ClientAuthenticationMethod>> methods(Set<AuthenticationMethod> methods){
        return (s)->{
            s.add(ClientAuthenticationMethod.CLIENT_SECRET_BASIC);
            s.add(ClientAuthenticationMethod.CLIENT_SECRET_POST);
        };
    }

    public static Consumer<Set<String>> scopes(Set<Scope> scopes){
        return (s)-> {
            s.add(OidcScopes.OPENID);
        };
    }

    public static Consumer<Set<AuthorizationGrantType>> grantTypes(Set<GrantType> grantTypes){
        return (s)-> grantTypes.forEach(a-> {
            AuthorizationGrantType value;
            switch (a.getGrantType()){
                case "client_credentials":
                    value = AuthorizationGrantType.CLIENT_CREDENTIALS;
                    break;
                default:
                    value = AuthorizationGrantType.AUTHORIZATION_CODE;
                    break;
            }
            s.add(value);
        });
    }


    public static Consumer<Set<String>> redirectUrls(Set<RedirectUrl> redirectUrls){
        return (s)-> redirectUrls.forEach(a-> s.add(a.getUrl()));
    }


}
