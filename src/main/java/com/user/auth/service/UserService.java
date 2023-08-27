package com.user.auth.service;

import com.user.auth.entity.user.User;
import com.user.auth.model.UserRegistration;

public interface UserService {
    User create(UserRegistration user);

    User addRole(String userId, String roleId);

    User findByUsernameOrEmail(String identifier);


    boolean checkIfUserAlreadyExist(String identifier);
}
