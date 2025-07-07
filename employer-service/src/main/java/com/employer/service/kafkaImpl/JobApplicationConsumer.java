package com.employer.service.kafkaImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.employer.service.entity.NotificationRequest;
import com.employer.service.repository.NotificationClient;

@Component
public class JobApplicationConsumer {

    private final NotificationClient notificationClient;
    
    private static final Logger log = LoggerFactory.getLogger(JobApplicationConsumer.class);

    @Autowired
    public JobApplicationConsumer(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    @KafkaListener(topics = "${job.application.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(String message) {
    	log.info("===========>"+message+"===========>");
        NotificationRequest request = new NotificationRequest();
        request.setTo("employer@example.com"); 
        request.setSubject("New Job Application");
        request.setBody("Notification: " + message);
        notificationClient.sendEmail(request);
    }
}
