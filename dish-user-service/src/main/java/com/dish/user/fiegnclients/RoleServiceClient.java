package com.dish.user.fiegnclients;

import com.dish.user.request.dto.RoleDto;
import com.dish.user.response.APIResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "DISH-ROLES-SERVICE",path = "${role.service.baseurl}")
public interface RoleServiceClient {

    @Operation(summary = "Save new role")
    @PostMapping(value = "create")
    public ResponseEntity<APIResponse> saveRole(@Valid @RequestBody RoleDto role);

    @Operation(summary = "Get all roles")
    @GetMapping(value = "get")
    public ResponseEntity<APIResponse> getRoles();

    @Operation(summary = "Get roles by id")
    @GetMapping(value = "get-by-id")
    public ResponseEntity<APIResponse> getrolesById(@RequestParam(value = "id",required = true) Long roleId);
}
