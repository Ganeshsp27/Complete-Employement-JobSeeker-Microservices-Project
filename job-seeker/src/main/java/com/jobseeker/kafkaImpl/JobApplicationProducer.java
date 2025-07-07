package com.jobseeker.kafkaImpl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class JobApplicationProducer {
	
	private static final Logger log = LoggerFactory.getLogger(JobApplicationProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic;

    public JobApplicationProducer(KafkaTemplate<String, String> kafkaTemplate, @Value("${job.application.topic}") String topic) {
	this.kafkaTemplate = kafkaTemplate;
	this.topic = topic;
}

    public void sendApplicationNotification(String message) {
    	log.info("===========>"+message+"===========>");
        kafkaTemplate.send(topic, message);
    }
}
