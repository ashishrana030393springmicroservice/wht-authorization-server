package com.user.auth.service;

import com.user.auth.entity.client.GrantType;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

public interface GrantService {
    GrantType createGrant(AuthorizationGrantType grantType);

    GrantType findByGrantType(AuthorizationGrantType authorizationGrantType);

    void save(GrantType grantType);
}
