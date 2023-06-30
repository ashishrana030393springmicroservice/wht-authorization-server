package com.user.auth.service;

import com.user.auth.entity.client.Scope;

public interface ScopeService {
    Scope findByScope(String oidcScope);

    Scope createScope(String scope);

    void save(Scope scope);

}
