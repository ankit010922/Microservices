package com.dish.roles.dto;

import java.util.List;

import com.dish.roles.annotations.ValidRole;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.val;

@Data
public class RoleDto {

	@NotBlank(message = "Role name can not be null or empty.")
	private String roleName;
	@NotBlank(message = "Role description can not be null or empty")
	private String roleDescription;
	@NotBlank(message = "Role code can not be empty or null")
	@ValidRole
	private String roleCode;
	@Valid
	private List<AuthorityDto> authorities;

	public void setRoleCode() {
		this.roleCode = roleCode != null ? roleCode.toUpperCase() : null;
	}

	public String getRoleCode() {
		return roleCode.toUpperCase();
	}
}
