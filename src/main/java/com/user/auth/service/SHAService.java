package com.user.auth.service;

import java.security.NoSuchAlgorithmException;

public interface SHAService {
    String verifier();

    String challenge(String verifier) throws NoSuchAlgorithmException;
}
