package com.dish.user.utills;

import com.dish.user.response.APIResponse;
import org.springframework.stereotype.Component;

import static com.dish.user.constant.UserConstants.SERVICE_NAME;
@Component
public class ApiResponseUtil {

    public APIResponse buildErrorResponse(String requestId, String errorMessage) {
        return APIResponse.builder()
                .status(Boolean.FALSE)
                .message(errorMessage)
                .requestId(requestId)
                .systemName(SERVICE_NAME)
                .build();
    }

    public APIResponse buildSuccessResponse(String requestId, String successMessage) {
        return APIResponse.builder()
                .status(Boolean.TRUE)
                .message(successMessage)
                .requestId(requestId)
                .systemName(SERVICE_NAME)
                .build();
    }
}
