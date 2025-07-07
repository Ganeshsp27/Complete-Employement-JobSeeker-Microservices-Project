package com.employer.service.kafkaImpl;

import com.employer.service.entity.NotificationRequest;
import com.employer.service.repository.NotificationClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.Mockito.*;

class JobApplicationConsumerTest {

    @Mock
    private NotificationClient notificationClient;

    @InjectMocks
    private JobApplicationConsumer consumer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListen() {
        String message = "New application received";

        consumer.listen(message);

        verify(notificationClient).sendEmail(argThat(req ->
            req.getTo().equals("employer@example.com") &&
            req.getSubject().equals("New Job Application") &&
            req.getBody().contains(message)
        ));
    }
}
