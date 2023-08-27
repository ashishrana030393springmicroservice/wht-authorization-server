package com.user.auth.model;

import com.user.auth.enums.Gender;
import com.user.auth.validators.ConfirmPassword;
import com.user.auth.validators.Dob;
import com.user.auth.validators.PasswordPolicy;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@ConfirmPassword
public class UserRegistration {
    private String username;
    @NotBlank
    @Dob
    private String dob;

    @com.user.auth.validators.Gender
    private String gender;
    @NotBlank
    private String firstName;
    private String lastName;
    @PasswordPolicy
    private String password;
    private String confirm;
}
