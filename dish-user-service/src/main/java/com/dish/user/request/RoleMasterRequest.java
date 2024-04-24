package com.dish.user.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoleMasterRequest {

	@NotNull(message = "Role Name is mandatory")
	private String roleName;
	
	@NotNull(message = "Role Code is mandatory")
	private String roleCode;
	@NotNull(message = "Role Description is mandatory")
	private String roleDescription;	
}
