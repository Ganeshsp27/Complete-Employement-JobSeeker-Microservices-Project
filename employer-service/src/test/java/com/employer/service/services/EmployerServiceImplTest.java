package com.employer.service.services;


import com.employer.service.entity.Employer;
import com.employer.service.entity.Job;
import com.employer.service.entity.NotificationRequest;
import com.employer.service.repository.EmployerRepository;
import com.employer.service.repository.JobRepository;
import com.employer.service.repository.NotificationClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployerServiceImplTest {

    @InjectMocks
    private EmployerServiceImpl employerService;

    @Mock
    private EmployerRepository employerRepo;

    @Mock
    private JobRepository jobRepo;

    @Mock
    private NotificationClient notificationClient;


@BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        Employer employer = new Employer();
        when(employerRepo.save(employer)).thenReturn(employer);

        Employer result = employerService.register(employer);

        assertEquals(employer, result);
        verify(employerRepo).save(employer);
    }

    @Test
    void testLogin() {
        Employer employer = new Employer();
        when(employerRepo.findByUsernameAndPassword("user", "pass")).thenReturn(employer);

        Employer result = employerService.login("user", "pass");

        assertEquals(employer, result);
        verify(employerRepo).findByUsernameAndPassword("user", "pass");
    }

    @Test
    void testPostJob() {
        Employer employer = new Employer();
        employer.setId(1L);

        Job job = new Job();
        job.setContactEmail("test@example.com");
        job.setJobTitle("Developer");

        when(employerRepo.findById(1L)).thenReturn(Optional.of(employer));
        when(jobRepo.save(any(Job.class))).thenAnswer(i -> i.getArgument(0));

        Job result = employerService.postJob(1L, job);

        assertEquals("Developer", result.getJobTitle());
        verify(notificationClient).sendEmail(any(NotificationRequest.class));
    }

    @Test
    void testEditJob() {
        Job job = new Job();
        job.setId(1L);
        when(jobRepo.findById(1L)).thenReturn(Optional.of(job));
        when(jobRepo.save(job)).thenReturn(job);

        Job result = employerService.editJob(1L, job);

        assertEquals(job, result);
        verify(jobRepo).save(job);
    }

    @Test
    void testDeleteJob() {
        doNothing().when(jobRepo).deleteById(1L);

        employerService.deleteJob(1L);

        verify(jobRepo).deleteById(1L);
    }

    @Test
    void testGetJobsByEmployer() {
        List<Job> jobs = List.of(new Job(), new Job());
        when(jobRepo.findByEmployerId(1L)).thenReturn(jobs);

        List<Job> result = employerService.getJobsByEmployer(1L);

        assertEquals(2, result.size());
        verify(jobRepo).findByEmployerId(1L);
    }
}

