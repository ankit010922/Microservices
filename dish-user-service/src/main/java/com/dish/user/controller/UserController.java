package com.dish.user.controller;

import com.dish.user.response.APIResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dish.user.constant.ControllerNames;
import com.dish.user.constant.UserConstants;
import com.dish.user.form.RegisterUserForm;
import com.dish.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping(UserConstants.API_V1 + ControllerNames.USER)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully registered user"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(ControllerNames.REGISTER_USER)
    public ResponseEntity<APIResponse> registerUser(@Valid @RequestBody RegisterUserForm registerUserForm){
        log.debug(UserConstants.REQUEST_BODY, registerUserForm);
        APIResponse apiResponse = userService.registerUser(registerUserForm);
        log.debug(UserConstants.RESPONSE_BODY, apiResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Operation(summary = "Fetch user details by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched user details"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/fetch/{id}")
    public ResponseEntity<APIResponse> getUser(@PathVariable Long id, WebRequest request, HttpServletRequest httpServletRequest) {
        log.debug("Get User By Id: {}", id);
        log.debug(httpServletRequest.toString());
        APIResponse apiResponse = userService.getUserDetails(id);
        log.debug(UserConstants.RESPONSE_BODY, apiResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
