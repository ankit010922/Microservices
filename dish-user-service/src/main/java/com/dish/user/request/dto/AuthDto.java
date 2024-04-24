package com.dish.user.request.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthDto {
    private final String email;
    private final String password;
}
