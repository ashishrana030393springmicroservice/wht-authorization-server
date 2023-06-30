package com.user.auth.service.impl.client;

import com.user.auth.dao.AuthenticationMethodRepository;
import com.user.auth.entity.client.AuthenticationMethod;
import com.user.auth.service.AuthenticationMethodService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationMethodServiceImp implements AuthenticationMethodService {
    private static final ClientAuthenticationMethod[] methods = {ClientAuthenticationMethod.CLIENT_SECRET_POST,ClientAuthenticationMethod.CLIENT_SECRET_BASIC};
    private final AuthenticationMethodRepository authenticationMethodRepository;

    @PostConstruct
    public void init(){
        for(int i=0; i<methods.length;i++){
            Optional<AuthenticationMethod> methodOptional = this.authenticationMethodRepository.findByAuthenticationMethod(methods[i].getValue());
            if(methodOptional.isEmpty()){
                createAuthenticationMethod(methods[i]);
            }
        }
    }

    @Override
    public AuthenticationMethod createAuthenticationMethod(ClientAuthenticationMethod method){
        return this.authenticationMethodRepository.save(new AuthenticationMethod(method.getValue(), new HashSet<>()));
    }

    @Override
    public AuthenticationMethod findAuthenticationMethod(ClientAuthenticationMethod method){
        return this.authenticationMethodRepository.findByAuthenticationMethod(method.getValue()).map(a->a).orElseThrow(()->new RuntimeException("invalid authentication method"));
    }

    @Override
    public AuthenticationMethod save(AuthenticationMethod authenticationMethod){
        return this.authenticationMethodRepository.save(authenticationMethod);
    }
}
