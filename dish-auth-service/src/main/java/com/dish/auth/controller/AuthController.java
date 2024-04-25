package com.dish.auth.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dish.auth.constants.Constants;
import com.dish.auth.constants.UrlConstants;
import com.dish.auth.dto.AuthDto;
import com.dish.auth.response.ApiResponse;
import com.dish.auth.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = UrlConstants.AUTH_CONTROLLER_BASEURL)
@Slf4j
@AllArgsConstructor
public class AuthController implements Constants{

	private final AuthService authService;

	@Operation(summary = "Save Auth")
	@PostMapping(value = "create")
	public ResponseEntity<ApiResponse> saveAuth(@Valid @RequestBody AuthDto auth, BindingResult bindingResult) {
		ApiResponse apiResponse = authService.saveAuth(auth,bindingResult);
		log.debug(API_RESPONSE,apiResponse);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
	}
	@Operation(summary = "Login")
    @PostMapping(value = "login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody AuthDto authDto, BindingResult bindingResult) {
        ApiResponse apiResponse = authService.login(authDto,bindingResult);
        log.debug(API_RESPONSE, apiResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
	
}
