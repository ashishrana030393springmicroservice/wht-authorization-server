package com.user.auth.validators;

import com.user.auth.validators.impl.DobValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DobValidator.class)
public @interface Dob {
    String message() default "invalid dob";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default{};
}
