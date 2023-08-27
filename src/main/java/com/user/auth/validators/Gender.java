package com.user.auth.validators;

import com.user.auth.validators.impl.GenderValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD})
@Constraint(validatedBy = GenderValidator.class)
public @interface  Gender {
    String message() default "invalid gender";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
