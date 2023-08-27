package com.user.auth.validators.impl;

import com.user.auth.validators.Gender;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<Gender, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return com.user.auth.enums.Gender.isValid(s);
    }
}
