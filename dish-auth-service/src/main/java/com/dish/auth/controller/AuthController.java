package com.dish.auth.controller;


import com.dish.auth.constants.UrlConstants;
import com.dish.auth.constants.Constants;
import com.dish.auth.dto.AuthDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.dish.auth.response.ApiResponse;
import com.dish.auth.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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
}
