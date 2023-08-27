package com.user.auth.validators.impl;

import com.user.auth.singleton.PasswordPolicyFactory;
import com.user.auth.validators.PasswordPolicy;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;

import java.util.ArrayList;
import java.util.List;

public class PasswordPolicyValidator implements ConstraintValidator<PasswordPolicy,String> {
    private static final int MIN_COMPLEX_RULES=PasswordPolicyFactory.getInstance().getMIN_COMPLEX_RULES();
    private static final int MIN_UPPER_CASE_CHARS = PasswordPolicyFactory.getInstance().getMIN_UPPER_CASE_CHARS();
    private static final int MIN_LOWER_CASE_CHARS=PasswordPolicyFactory.getInstance().getMIN_LOWER_CASE_CHARS();
    private static final int MIN_DIGIT_CASE_CHARS=PasswordPolicyFactory.getInstance().getMIN_DIGIT_CASE_CHARS();
    private static final int MIN_SPECIAL_CASE_CHARS=PasswordPolicyFactory.getInstance().getMIN_SPECIAL_CASE_CHARS();
    private static final int MAX_REPETATIVE_CHARS=PasswordPolicyFactory.getInstance().getMAX_REPETATIVE_CHARS();
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        PasswordPolicyFactory.PasswordPolicyDef instance = PasswordPolicyFactory.getInstance();
        instance.getMaxRepetativeChars();
        List<Rule> passwordRules = new ArrayList<>();
        passwordRules.add(new LengthRule(10,128));
        CharacterCharacteristicsRule passwordChars = new CharacterCharacteristicsRule(MIN_COMPLEX_RULES,
                new CharacterRule(EnglishCharacterData.UpperCase, MIN_UPPER_CASE_CHARS),
                new CharacterRule(EnglishCharacterData.LowerCase,MIN_LOWER_CASE_CHARS),
                new CharacterRule(EnglishCharacterData.Digit,MIN_DIGIT_CASE_CHARS),
                new CharacterRule(EnglishCharacterData.Special,MIN_SPECIAL_CASE_CHARS));
        passwordRules.add(passwordChars);
        passwordRules.add(new RepeatCharacterRegexRule(MAX_REPETATIVE_CHARS));
        PasswordValidator passwordValidator =  new PasswordValidator(passwordRules);
        PasswordData passwordData = new PasswordData(password);
        RuleResult ruleResult = passwordValidator.validate(passwordData);
        constraintValidatorContext.disableDefaultConstraintViolation();
        for(RuleResultDetail detail: ruleResult.getDetails()){
            customMessageForValidation(constraintValidatorContext,detail.getErrorCode());
        }
        return ruleResult.isValid();
    }

    private void customMessageForValidation(ConstraintValidatorContext constraintValidatorContext,String message){
        constraintValidatorContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
