package com.user.auth;

import com.user.auth.entity.client.AuthenticationMethod;
import com.user.auth.entity.client.GrantType;
import com.user.auth.entity.client.RedirectUrl;
import com.user.auth.entity.client.Scope;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.Set;
import java.util.function.Consumer;

public class Utils {
    public static Consumer<Set<ClientAuthenticationMethod>> methods(Set<AuthenticationMethod> methods){
        return (s)-> methods
                .forEach(a-> s.add(new ClientAuthenticationMethod(a.getAuthenticationMethod())));
    }

    public static Consumer<Set<String>> scopes(Set<Scope> scopes){
        return (s)-> scopes.forEach(a-> s.add(a.getScope()));
    }

    public static Consumer<Set<AuthorizationGrantType>> grantTypes(Set<GrantType> grantTypes){
        return (s)-> grantTypes.forEach(a-> s.add(new AuthorizationGrantType(a.getGrantType())));
    }

    public static Consumer<Set<String>> redirectUrls(Set<RedirectUrl> redirectUrls){
        return (s)-> redirectUrls.forEach(a-> s.add(a.getUrl()));
    }


}
