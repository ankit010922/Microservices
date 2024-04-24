package com.dish.user.request.dto;


import lombok.Data;

@Data
public class AuthorityDto {

	private String serviceName;
	private String controllerUrl;
	private String accessType;
}
