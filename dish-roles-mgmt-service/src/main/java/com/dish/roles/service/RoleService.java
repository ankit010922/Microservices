package com.dish.roles.service;

import org.springframework.validation.BindingResult;

import com.dish.roles.dto.RoleDto;
import com.dish.roles.response.ApiResponse;

public interface RoleService {
	ApiResponse saveRole(RoleDto role, BindingResult bindingResult);

	ApiResponse findAll();

    ApiResponse findById(Long roleId);
}
