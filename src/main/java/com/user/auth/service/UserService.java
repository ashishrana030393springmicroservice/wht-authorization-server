package com.user.auth.service;

import com.user.auth.entity.user.User;

public interface UserService {
    User create(String username,String email, String rawPassword);

    User addRole(String userId, String roleId);

    User findByUsernameOrEmail(String identifier);
}
