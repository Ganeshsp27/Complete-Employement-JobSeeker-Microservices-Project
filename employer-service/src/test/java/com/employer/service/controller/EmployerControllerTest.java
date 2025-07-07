package com.employer.service.controller;

import com.employer.service.entity.Employer;
import com.employer.service.entity.Job;
import com.employer.service.services.EmployerService;
import com.employer.service.repository.NotificationClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployerControllerTest {

    @InjectMocks
    private EmployerController controller;

    @Mock
    private EmployerService employerService;

    @Mock
    private NotificationClient notificationClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        Employer employer = new Employer();
        when(employerService.register(employer)).thenReturn(employer);

        ResponseEntity<Employer> response = controller.register(employer);

        assertEquals(employer, response.getBody());
    }

    @Test
    void testLogin() {
        Employer employer = new Employer();
        when(employerService.login("user", "pass")).thenReturn(employer);

        ResponseEntity<Employer> response = controller.login("user", "pass");

        assertEquals(employer, response.getBody());
    }

//    @Test
//    void testPostJob() {
//        Job job = new Job();
//        when(employerService.postJob(1L, job)).thenReturn(job);
//
//        ResponseEntity<Job> response = controller.postJob(1L, job);
//
//        assertEquals(job, response.getBody());
//    }
//
//    @Test
//    void testEditJob() {
//        Job job = new Job();
//        when(employerService.editJob(1L, job)).thenReturn(job);
//
//        ResponseEntity<Job> response = controller.editJob(1L, job);
//
//        assertEquals(job, response.getBody());
//    }

    @Test
    void testDeleteJob() {
        doNothing().when(employerService).deleteJob(1L);

        ResponseEntity<Void> response = controller.deleteJob(1L);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testGetJobs() {
        List<Job> jobs = List.of(new Job(), new Job());
        when(employerService.getJobsByEmployer(1L)).thenReturn(jobs);

        ResponseEntity<List<Job>> response = controller.getJobs(1L);

        assertEquals(2, response.getBody().size());
    }

//    @Test
//    void testCallService() {
//        when(notificationClient.sendEmail(any())).thenReturn("Email sent");
//
//        String result = controller.callService();
//
//        assertTrue(result.contains("Notification sent"));
//    }
//
//    @Test
//    void testFallback() {
//        String result = controller.fallback(new RuntimeException("Service down"));
//
//        assertEquals("Fallback response: Notification service is unavailable.", result);
//    }
}
