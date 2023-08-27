package com.user.auth.service.impl.client;

import com.user.auth.dao.ScopeRepository;
import com.user.auth.entity.client.Client;
import com.user.auth.entity.client.Scope;
import com.user.auth.service.ScopeService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ScopeServiceImp implements ScopeService {
    public static final String[] scopes = {OidcScopes.OPENID};
    private final ScopeRepository scopeRepository;
//    @PostConstruct
//    public void init(){
//        for(int i = 0; i<scopes.length; i++){
//           Optional<Scope> scopeOptional = this.scopeRepository.findByScope(scopes[i]);
//           if(scopeOptional.isEmpty()){
//               createScope(scopes[i]);
//           }
//        }
//    }

    @Override
    public Scope findByScope(String oidcScope) {
        return this.scopeRepository.findByScope(oidcScope).map(oidc->oidc).orElseThrow(()-> new RuntimeException("invalid scope"));
    }

    @Override
    public Scope createScope(String scope){
        return this.scopeRepository.save(new Scope(scope, new HashSet<Client>()));
    }

    @Override
    public void save(Scope scope) {
        this.scopeRepository.save(scope);
    }
}
