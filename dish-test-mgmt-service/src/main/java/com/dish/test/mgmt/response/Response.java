package com.dish.test.mgmt.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Response<T> {

	private int statusCode;
	private String message;
	private T data;
}
