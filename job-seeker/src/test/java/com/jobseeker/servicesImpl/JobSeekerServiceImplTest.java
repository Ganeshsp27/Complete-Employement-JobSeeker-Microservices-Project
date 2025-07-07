package com.jobseeker.servicesImpl;

import com.jobseeker.entity.Job;
import com.jobseeker.entity.JobSeeker;
import com.jobseeker.common.*;
import com.jobseeker.kafkaImpl.JobApplicationProducer;
import com.jobseeker.repository.JobRepository;
import com.jobseeker.repository.JobSeekerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JobSeekerServiceImplExceptionTest {

    @Mock
    private JobSeekerRepository jobSeekerRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private JobApplicationProducer jobApplicationProducer;

    @InjectMocks
    private JobSeekerServiceImpl jobSeekerService;


@BeforeEach
void setUp() {
 MockitoAnnotations.openMocks(this);
}

   void RegisterThrowsUserAlreadyExistsException() {
        JobSeeker seeker = new JobSeeker();
        seeker.setUsername("john");
        seeker.setPassword("pass");

        when(jobSeekerRepository.findByUsernameAndPassword("john", "pass")).thenReturn(seeker);

        assertThrows(UserAlreadyExistsException.class, () -> jobSeekerService.register(seeker));
    }

    @Test
    void testLoginThrowsInvalidCredentialsException() {
        when(jobSeekerRepository.findByUsernameAndPassword("invalid", "wrong")).thenReturn(null);

        assertThrows(InvalidCredentialsException.class, () -> jobSeekerService.login("invalid", "wrong"));
    }

    @Test
    void testApplyForJobThrowsJobNotFoundException() {
        when(jobRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(JobNotFoundException.class, () -> jobSeekerService.applyForJob(999L));
    }
}
