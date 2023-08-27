package com.user.auth.model;

import com.user.auth.validators.Dob;
import com.user.auth.validators.Gender;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BasicInfo {
    @NotBlank
    @Dob
    private String dob;
    @NotBlank
    @Gender
    private String gender;
}
