package com.notification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notification.entity.NotificationRequest;

@RestController
@RequestMapping("/notify")
public class NotificationController {

    @PostMapping("/email")
    public ResponseEntity<String> sendEmail(@RequestBody NotificationRequest request) {
        System.out.println("Sending email to: " + request.getTo());
        return ResponseEntity.ok("Email sent to " + request.getTo());
    }
}

