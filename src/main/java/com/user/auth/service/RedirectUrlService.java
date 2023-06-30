package com.user.auth.service;

import com.user.auth.entity.client.RedirectUrl;

public interface RedirectUrlService {
    RedirectUrl findByUrl(String url);

    void save(RedirectUrl redirectUrl);
}
