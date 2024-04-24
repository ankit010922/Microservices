package com.dish.auth.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dish.auth.response.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler({NullPointerException.class})
    public ResponseEntity<ApiResponse> handleGlobalException(Exception exception) {
	log.error("Inside global exception handler for Null Pointer exception.");
        ApiResponse response =  ApiResponse.builder().status(Boolean.FALSE).data(null).HTTPstatusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage()).build();
        return new ResponseEntity<ApiResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}