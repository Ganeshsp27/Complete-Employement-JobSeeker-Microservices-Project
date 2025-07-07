package com.jobseeker.kafkaImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class JobApplicationProducerTest {

    private KafkaTemplate<String, String> kafkaTemplate;
    private JobApplicationProducer producer;

    @BeforeEach
    void setUp() {
        kafkaTemplate = mock(KafkaTemplate.class);
        producer = new JobApplicationProducer(kafkaTemplate, "jobApplicationTopic");
    }

    @Test
    void testSendApplicationNotification() {
        String message = "Job ID 101 has been applied for.";

        producer.sendApplicationNotification(message);

        ArgumentCaptor<String> topicCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);

        verify(kafkaTemplate, times(1)).send(topicCaptor.capture(), messageCaptor.capture());

        assertEquals("jobApplicationTopic", topicCaptor.getValue());
        assertEquals(message, messageCaptor.getValue());
    }
}
