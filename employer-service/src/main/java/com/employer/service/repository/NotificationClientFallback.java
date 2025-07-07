package com.employer.service.repository;

import org.springframework.stereotype.Component;

import com.employer.service.entity.NotificationRequest;

@Component
public class NotificationClientFallback implements NotificationClient {
    @Override
    public String sendEmail(NotificationRequest request) {
        return "Notification service is down. Email not sent.";
    }
}

