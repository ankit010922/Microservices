package com.dish.roles.controller;

import com.dish.roles.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.dish.roles.constants.UrlConstants;
import com.dish.roles.dto.RoleDto;
import com.dish.roles.response.ApiResponse;
import com.dish.roles.service.RoleService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = UrlConstants.ROLE_CONTROLLER_BASEURL)
@Slf4j
public class RoleController implements Constants{

	@Autowired
	RoleService roleService;

	@Operation(summary = "Save new role")
	@PostMapping(value = "create")
	public ResponseEntity<ApiResponse> saveRole(@Valid @RequestBody RoleDto role, BindingResult bindingResult) {
		ApiResponse apiResponse = roleService.saveRole(role,bindingResult);
		log.debug(API_RESPONSE,apiResponse);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
	}

	@Operation(summary = "Get all roles")
	@GetMapping(value = "get")
	public ResponseEntity<ApiResponse> getRoles() {
		ApiResponse apiResponse = roleService.findAll();
		log.debug(API_RESPONSE,apiResponse);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
		
	}

	@Operation(summary = "Get roles by id")
	@GetMapping(value = "get-by-id")
	public ResponseEntity<ApiResponse> getrolesById(@RequestParam(value = "id",required = true) Long roleId){
		ApiResponse apiResponse = roleService.findById(roleId);
		log.debug(API_RESPONSE,apiResponse);
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);

	}
}
