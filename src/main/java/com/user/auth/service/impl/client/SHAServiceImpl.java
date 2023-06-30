package com.user.auth.service.impl.client;

import com.user.auth.service.SHAService;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class SHAServiceImpl implements SHAService {
    @Override
    public String verifier(){
        SecureRandom secureRandom = new SecureRandom();
        byte[] code = new byte[32];
        secureRandom.nextBytes(code);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(code);
    }

    @Override
    public String challenge(String verifier) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] digested = digest.digest(verifier.getBytes());
        return Base64.getUrlEncoder().withoutPadding().encodeToString(digested);
    }
}
