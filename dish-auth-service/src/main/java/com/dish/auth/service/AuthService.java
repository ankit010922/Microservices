package com.dish.auth.service;


import com.dish.auth.dto.AuthDto;
import com.dish.auth.response.ApiResponse;
import org.springframework.validation.BindingResult;



public interface AuthService {
	ApiResponse saveAuth(AuthDto authDto, BindingResult bindingResult);

	ApiResponse login(AuthDto authDto, BindingResult bindingResult);

}
