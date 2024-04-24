package com.dish.api.gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dish.api.gateway.util.APIResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandller {


	@ExceptionHandler(value = UnAuthorizedExc.class)
	public ResponseEntity<APIResponseDTO> unAuthorizedException(UnAuthorizedExc unAuthorizedException)
	{
		return new ResponseEntity<>(APIResponseDTO.builder()
				.message(unAuthorizedException.getMessage())
				.success(false)
				.timeStamp(System.currentTimeMillis())
				.build(),HttpStatus.BAD_GATEWAY);

	}

}
