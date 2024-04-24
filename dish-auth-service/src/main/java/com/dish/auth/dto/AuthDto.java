package com.dish.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthDto {
    @Email(message = "Email should be in proper email format")
    private String email;
    @NotBlank(message = "Password can not be blank")
    private String password;
}
