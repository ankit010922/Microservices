package com.dish.user.fiegnclients.resolvers;

import com.dish.user.fiegnclients.AuthServiceClient;
import com.dish.user.request.dto.AuthDto;
import com.dish.user.response.APIResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class AuthServiceClientResolver {
    private final AuthServiceClient authServiceClient;
    public Optional<APIResponse> saveAuth(AuthDto authDto){
        ResponseEntity<APIResponse> response = authServiceClient.saveAuth(authDto);
        return Optional.ofNullable((APIResponse) response.getBody());
    }
}
