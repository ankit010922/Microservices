package com.dish.user.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@ToString
@Builder(toBuilder = true)
public class APIResponse<T> {
	private Boolean status;
	private String message;
	private HttpStatus HTTPstatusCode;
	private String requestId;
	private T data;
	private  String systemName;
	@Builder.Default
	private Long timeStamp = System.currentTimeMillis();

}