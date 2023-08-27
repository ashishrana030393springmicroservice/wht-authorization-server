package com.user.auth.service.impl.client;

import com.user.auth.dao.GrantRepository;
import com.user.auth.entity.client.GrantType;
import com.user.auth.service.GrantService;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@AllArgsConstructor
public class GrantServiceImp implements GrantService {
    private static final AuthorizationGrantType[] grants = {AuthorizationGrantType.AUTHORIZATION_CODE,AuthorizationGrantType.CLIENT_CREDENTIALS};
    private final GrantRepository grantRepository;

//    @PostConstruct
//    public void init(){
//        for(int i = 0; i<grants.length; i++){
//            Optional<GrantType> byGrantType = this.grantRepository.findByGrantType(grants[i].getValue());
//            if(byGrantType.isEmpty()){
//                createGrant(grants[i]);
//            }
//        }
//    }

    @Override
    public GrantType createGrant(AuthorizationGrantType grantType){
        return this.grantRepository.save(new GrantType(grantType.getValue(),new HashSet<>()));
    }

    @Override
    public GrantType findByGrantType(AuthorizationGrantType authorizationGrantType) {
        return this.grantRepository.findByGrantType(authorizationGrantType.getValue()).map(gt->gt).orElseThrow(()->new RuntimeException("invalid authorization grant type"));
    }

    @Override
    public void save(GrantType grantType) {
        this.grantRepository.save(grantType);
    }
}
