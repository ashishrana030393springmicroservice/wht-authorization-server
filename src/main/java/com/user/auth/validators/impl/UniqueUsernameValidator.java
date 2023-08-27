package com.user.auth.validators.impl;

import com.user.auth.service.UserService;
import com.user.auth.validators.UniqueUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername,String> {
    private final UserService userService;

    public UniqueUsernameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean exist = this.userService.checkIfUserAlreadyExist(s);
        return !exist;
    }
}