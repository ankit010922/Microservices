package com.dish.notification.config;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.dish.notification.EmailNotificationRequest;
import com.dish.notification.service.EmailNotificationService;
import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationEventHandler {
 
    private final EmailNotificationService emailNotificationService;
    
    @KafkaListener(topics = "email_notification")
    public void listener(String message) {
	try{Gson gson = new Gson();
	EmailNotificationRequest emailNotificationRequest = gson.fromJson(message, EmailNotificationRequest.class);
	emailNotificationService.sendEmailWithOtp(emailNotificationRequest.getEmail());
    
    }
    catch (Exception e) {
        log.info("Error: " + e.getMessage());
    }
    }
}

