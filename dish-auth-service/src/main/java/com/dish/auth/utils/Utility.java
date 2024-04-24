package com.dish.auth.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Utility {
    private final PasswordEncoder passwordEncoder;
    public String encodePassword(String password){
        return passwordEncoder.encode(password);
    }
    public boolean isPasswordMatch(String password,String storedPassword){
        return passwordEncoder.matches(password,storedPassword);
    }
}
