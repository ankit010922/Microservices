package com.dish.auth.service.impl;


import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.dish.auth.configuration.MessageConfig;
import com.dish.auth.constants.Constants;
import com.dish.auth.dto.AuthDto;
import com.dish.auth.entity.AuthEntity;
import com.dish.auth.response.ApiResponse;
import com.dish.auth.respository.AuthRepository;
import com.dish.auth.service.AuthService;
import com.dish.auth.utils.Utility;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

private final AuthRepository authRepository;
private final MessageConfig messageConfig;
private final Utility utility;
	@Override
	public ApiResponse saveAuth(AuthDto authDto, BindingResult bindingResult) {
		log.debug("*****************Save Auth******************");
		String uuid = UUID.randomUUID().toString();
		log.debug(Constants.REQUEST_ID, uuid);
		try {
			if (bindingResult.hasErrors()) {
				StringBuilder errorMessage = new StringBuilder();
				bindingResult.getAllErrors().forEach(error -> {
					errorMessage.append(error.getDefaultMessage()).append("; ");
				});
				return ApiResponse.builder().data(null).status(Boolean.FALSE).HTTPstatusCode(HttpStatus.BAD_REQUEST)
						.message(errorMessage.toString()).requestId(uuid).build();

			}
			AuthEntity authEntity = new AuthEntity();
			BeanUtils.copyProperties(authDto, authEntity);
			authEntity.setPassword(utility.encodePassword(authDto.getPassword()));
			authRepository.save(authEntity);
			return ApiResponse.builder().data(null).status(Boolean.TRUE).HTTPstatusCode(HttpStatus.OK)
					.message(messageConfig.authDetailsSavedSuccessfully).requestId(uuid).build();
		} catch (DataIntegrityViolationException  e) {
			return ApiResponse.builder().data(null).status(Boolean.FALSE).HTTPstatusCode(HttpStatus.BAD_REQUEST)
					.message(messageConfig.emailDuplicate).requestId(uuid).build();
		} catch (Exception e) {
			log.error("Exception : ()", e.getMessage());
			return ApiResponse.builder().data(null).status(Boolean.FALSE)
					.HTTPstatusCode(HttpStatus.INTERNAL_SERVER_ERROR).message(messageConfig.adminContact).build();
		}
	}
	
	@Override
	public ApiResponse login(AuthDto authDto, BindingResult bindingResult) {
	    log.debug("*****************Login******************");
	    String uuid = UUID.randomUUID().toString();
	    log.debug(Constants.REQUEST_ID, uuid);
	    
	    // Check if there are binding errors
	    if (bindingResult.hasErrors()) {
	        StringBuilder errorMessage = new StringBuilder();
	        bindingResult.getAllErrors().forEach(error -> {
	            errorMessage.append(error.getDefaultMessage()).append("; ");
	        });
	        return ApiResponse.builder()
	                .data(null)
	                .status(Boolean.FALSE)
	                .HTTPstatusCode(HttpStatus.BAD_REQUEST)
	                .message(errorMessage.toString())
	                .requestId(uuid)
	                .build();
	    }
	    
	    try {
	        // Find user by email
	        Optional<AuthEntity> authOptional = authRepository.findByEmail(authDto.getEmail());
	        if (!authOptional.isPresent()) {
	            return ApiResponse.builder()
	                    .data(null)
	                    .status(Boolean.FALSE)
	                    .HTTPstatusCode(HttpStatus.UNAUTHORIZED)
	                    .message(messageConfig.invalidCredentials)
	                    .requestId(uuid)
	                    .build();
	        }
	        
	        AuthEntity authEntity = authOptional.get();
	        // Check if password matches
	        if (!authEntity.getPassword().equals(authDto.getPassword())) {
	            return ApiResponse.builder()
	                    .data(null)
	                    .status(Boolean.FALSE)
	                    .HTTPstatusCode(HttpStatus.UNAUTHORIZED)
	                    .message(messageConfig.invalidCredentials)
	                    .requestId(uuid)
	                    .build();
	        }
	        
	        // If authentication successful
	        return ApiResponse.builder()
	                .data(null)
	                .status(Boolean.TRUE)
	                .HTTPstatusCode(HttpStatus.OK)
	                .message(messageConfig.loginSuccessful)
	                .requestId(uuid)
	                .build();
	    } catch (Exception e) {
	        log.error("Exception : ()", e.getMessage());
	        return ApiResponse.builder()
	                .data(null)
	                .status(Boolean.FALSE)
	                .HTTPstatusCode(HttpStatus.INTERNAL_SERVER_ERROR)
	                .message(messageConfig.adminContact)
	                .requestId(uuid)
	                .build();
	    }
	}

	}