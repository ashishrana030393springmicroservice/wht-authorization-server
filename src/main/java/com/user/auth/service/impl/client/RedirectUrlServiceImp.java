package com.user.auth.service.impl.client;

import com.user.auth.config.AppConfig;
import com.user.auth.dao.RedirectUrlRepository;
import com.user.auth.entity.client.RedirectUrl;
import com.user.auth.service.RedirectUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class RedirectUrlServiceImp implements RedirectUrlService {
    private final AppConfig appConfig;
    private static final String[] urls ={ "/signin-callback","/assets/silent-callback.html"};
    private final RedirectUrlRepository redirectUrlRepository;

//    @PostConstruct
//    public void init(){
//        for(int i = 0; i< urls.length; i++){
//            Optional<RedirectUrl> urlOptional = this.redirectUrlRepository.findByUrl(appConfig.getUi1().getUrl() + urls[i]);
//            if(urlOptional.isEmpty()){
//                createRedirectUrls(appConfig.getUi1().getUrl() + urls[i]);
//            }
//        }
//    }

    public RedirectUrl createRedirectUrls(String url){
        return this.redirectUrlRepository.save(new RedirectUrl(url,new HashSet<>()));
    }

    @Override
    public RedirectUrl findByUrl(String url) {
        return this.redirectUrlRepository.findByUrl(url).map(rdUrl->rdUrl).orElseThrow(()->new RuntimeException("invalid redirect url"));
    }

    @Override
    public void save(RedirectUrl redirectUrl) {
        this.redirectUrlRepository.save(redirectUrl);
    }
}
