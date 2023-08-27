package com.user.auth.resource;

import com.user.auth.config.Constant;
import com.user.auth.entity.user.User;
import com.user.auth.model.BasicInfo;
import com.user.auth.model.PersonalDetail;
import com.user.auth.model.UserRegistration;
import com.user.auth.model.UsernameInfo;
import com.user.auth.service.UserService;
import com.user.auth.singleton.PasswordPolicyFactory;
import jakarta.validation.*;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
public class UserResource {
    private final UserService userService;
    private final RabbitTemplate template;
    @PostMapping("validatepersonaldetails")
    ResponseEntity<?> validatePersonalDetails(@RequestBody PersonalDetail personalDetail){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<PersonalDetail>> violations = validator.validate(personalDetail);
        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
        return ResponseEntity.ok(personalDetail);
    }
    @PostMapping("validatebasicinfo")
    ResponseEntity<?>validateBasicInfo(@RequestBody BasicInfo basicInfo){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<BasicInfo>> violations = validator.validate(basicInfo);
        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
        return ResponseEntity.ok(basicInfo);
    }

    @PostMapping("validateusername")
    ResponseEntity<?> validateUsername(@Valid @RequestBody UsernameInfo usernameInfo){
        return ResponseEntity.ok(usernameInfo);
    }

    @PostMapping("sign-up")
    ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistration userRegistration){
        template.convertAndSend(Constant.SIGNUP_EXCHANGE,Constant.SIGNUP,userRegistration);
        User user = this.userService.create(userRegistration);
        return ResponseEntity.ok(user);
    }

    @GetMapping("password-policy")
    ResponseEntity<?> getPasswordPolicy(){
        return ResponseEntity.ok(PasswordPolicyFactory.getInstance());
    }

    @PostMapping("test")
    ResponseEntity<?> rabbitTest( @RequestBody UserRegistration userRegistration){
        template.convertAndSend(Constant.SIGNUP_EXCHANGE,Constant.SIGNUP,userRegistration);
        return ResponseEntity.ok().body(null);
    }
}
