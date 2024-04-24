package com.dish.user.exception;

import com.dish.user.response.APIResponse;
import feign.RetryableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.UnknownHostException;

@ControllerAdvice
public class GlobalExceptionHandler {

	// Handler for DuplicateImeiException
    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<?> handleDuplicateImeiException(DuplicateException ex, WebRequest request) {
        // Create a custom response body or use a simple message
    	APIResponse dishApiResponse =  APIResponse.builder().message(ex.getMessage()).build();
    	return new ResponseEntity<>(dishApiResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UnknownHostException.class)
    public ResponseEntity handleUnknownHostException(UnknownHostException ex){
        APIResponse apiResponse = APIResponse.builder().message(ex.getMessage()).build();
        return new ResponseEntity(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(RetryableException.class)
    public ResponseEntity handleRetryableException(RetryableException ex){
        APIResponse apiResponse = APIResponse.builder().message(ex.getMessage()).build();
        return new ResponseEntity(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
