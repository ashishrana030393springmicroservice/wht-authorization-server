package com.user.auth.validators.impl;

import com.user.auth.model.UserRegistration;
import com.user.auth.validators.ConfirmPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword,Object> {
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        UserRegistration u = ((UserRegistration) o);
        return u.getPassword().equals(u.getConfirm());
    }
}