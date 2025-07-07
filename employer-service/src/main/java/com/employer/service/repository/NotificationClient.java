package com.employer.service.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.employer.service.entity.NotificationRequest;

@FeignClient(name = "notification-service", fallback = NotificationClientFallback.class)
public interface NotificationClient {
    @PostMapping("/notify/email")
    String sendEmail(@RequestBody NotificationRequest request);
}
