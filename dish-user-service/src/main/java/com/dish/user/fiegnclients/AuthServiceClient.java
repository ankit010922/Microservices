package com.dish.user.fiegnclients;

import com.dish.user.request.dto.AuthDto;
import com.dish.user.response.APIResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "DISH-AUTH-SERVICE",path = "${auth.service.baseurl}")
public interface AuthServiceClient {
    @PostMapping(value = "create")
    public ResponseEntity<APIResponse> saveAuth(@Valid @RequestBody AuthDto auth);
}
