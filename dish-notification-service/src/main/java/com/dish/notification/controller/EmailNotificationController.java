package com.dish.notification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dish.notification.service.EmailNotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
public class EmailNotificationController {

    private final EmailNotificationService emailNotificationService;

    
    @PostMapping("/send-email")
    public ResponseEntity<Void> sendEmailWithOtp(@RequestParam("email") String email){
    	emailNotificationService.sendEmailWithOtp(email);
		return null;
    }
    
}
