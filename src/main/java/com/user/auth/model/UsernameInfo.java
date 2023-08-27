package com.user.auth.model;

import com.user.auth.validators.UniqueUsername;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsernameInfo {
    @NotBlank
    @UniqueUsername
    private String username;

}
