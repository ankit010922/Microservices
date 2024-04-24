package com.dish.user.fiegnclients.resolvers;

import com.dish.user.fiegnclients.RoleServiceClient;
import com.dish.user.request.dto.RoleDto;
import com.dish.user.response.APIResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class RoleServClientRespResolver {

private final RoleServiceClient roleServiceClient;
    public Optional<Object> getRoleDto(long roleId){
           ResponseEntity<APIResponse> response = roleServiceClient.getrolesById(roleId);
           return Optional.ofNullable( response.getBody().getData());
    }
}
