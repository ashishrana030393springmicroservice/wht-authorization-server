package com.user.auth.service;

import com.user.auth.entity.client.AuthenticationMethod;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

public interface AuthenticationMethodService {
    AuthenticationMethod createAuthenticationMethod(ClientAuthenticationMethod method);

    AuthenticationMethod findAuthenticationMethod(ClientAuthenticationMethod method);

    AuthenticationMethod save(AuthenticationMethod authenticationMethod);
}
