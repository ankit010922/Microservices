package com.dish.test.mgmt.utility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(Include.NON_NULL)
public class DishApiResponse {

	private int statusCode;
	private String message;
	private Object data;
	private Long totalRecords;
}
