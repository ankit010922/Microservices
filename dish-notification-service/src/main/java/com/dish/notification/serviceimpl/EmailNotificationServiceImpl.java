package com.dish.notification.serviceimpl;

import java.io.IOException;
import org.apache.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dish.notification.service.EmailNotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailNotificationServiceImpl implements EmailNotificationService {
    
    private static final String HEADER_CUSTOMER_KEY = "Customer-Key";
    private static final String HEADER_TIMESTAMP = "Timestamp";
    private static final String HEADER_AUTHORIZATION = "Authorization";
    
    @Autowired
    private ObjectMapper objectMapper;

    @Value("${user.xecurify.customer.key}")
    private String customerKey;

    @Value("${user.xecurify.sendotp.url}")
    private String sendOtpUrl;

    @Value("${user.xecurify.api.key}")
    private String apiKey;

    @Override
    public void sendEmailWithOtp(String email) {
        try {
            Map<String, Object> requestMap = new HashMap<>();
            requestMap.put("customerKey", customerKey);
            requestMap.put("email", email);
            requestMap.put("authType", "EMAIL");
            requestMap.put("transactionName", "CUSTOM-OTP-VERIFICATION");
            String jsonRequestString = objectMapper.writeValueAsString(requestMap);

            HttpResponse httpResponse = sendRequestToAuthenticationServer(jsonRequestString, sendOtpUrl);
            log.info("API Response ::==>{}", httpResponse);
        } catch (Exception e) {
            log.info("Error ===>", e);
        }
    }

    private HttpResponse sendRequestToAuthenticationServer(String requestJson, String url) throws IOException {
        String currentTimeInMillis = String.valueOf(System.currentTimeMillis());
        String stringToHash = customerKey + currentTimeInMillis + apiKey;
        String hashValue = new Sha512Hash(stringToHash).toHex().toLowerCase();

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost postRequest = new HttpPost(url);

            StringEntity input = new StringEntity(requestJson);  
            input.setContentType("application/json");
            postRequest.setEntity(input);

            postRequest.setHeader(HEADER_CUSTOMER_KEY, customerKey);
            postRequest.setHeader(HEADER_TIMESTAMP, currentTimeInMillis);
            postRequest.setHeader(HEADER_AUTHORIZATION, hashValue);

            return (HttpResponse) httpClient.execute(postRequest);
        }
    }
}
