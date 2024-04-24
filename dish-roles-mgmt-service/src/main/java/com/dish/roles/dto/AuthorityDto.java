package com.dish.roles.dto;


import com.dish.roles.annotations.AccessTypeValidator;
import com.dish.roles.enums.AccessType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthorityDto {
	@NotBlank(message = "Service name can not be empty")
	private String serviceName;
	@NotBlank(message = "Controller URL can not be empty")
	private String controllerUrl;
	@AccessTypeValidator
	private String accessType;
}
