package com.employer.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employer.service.common.EmployerNotFoundException;
import com.employer.service.common.InvalidCredentialsException;
import com.employer.service.common.JobNotFoundException;
import com.employer.service.entity.Employer;
import com.employer.service.entity.Job;
import com.employer.service.entity.NotificationRequest;
import com.employer.service.repository.EmployerRepository;
import com.employer.service.repository.JobRepository;
import com.employer.service.repository.NotificationClient;

@Service
public class EmployerServiceImpl implements EmployerService {
    @Autowired private EmployerRepository employerRepo;
    @Autowired private JobRepository jobRepo;
    
    @Autowired
    private NotificationClient notificationClient;

    public Employer register(Employer employer) {
        return employerRepo.save(employer);
    }

//    public Employer login(String username, String password) {
//        return employerRepo.findByUsernameAndPassword(username, password);
//    }
    
    @Override
    public Employer login(String username, String password) {
        Employer employer = employerRepo.findByUsernameAndPassword(username, password);
        if (employer == null) {
            throw new InvalidCredentialsException("Invalid username or password");
        }
        return employer;
    }


 
//    @Override
//    public Job postJob(Long employerId, Job job) {
//        Employer employer = employerRepo.findById(employerId).orElseThrow();
//        job.setEmployer(employer);
//        Job savedJob = jobRepo.save(job);
//
//        NotificationRequest request = new NotificationRequest();
//        request.setTo(job.getContactEmail());
//        request.setSubject("Job Posted Successfully");
//        request.setBody("Your job '" + job.getJobTitle() + "' has been posted.");
//
//        notificationClient.sendEmail(request); // Feign + Circuit Breaker
//
//        return savedJob;
//    }
    
    @Override
    public Job postJob(Long employerId, Job job) {
        try {
            Employer employer = employerRepo.findById(employerId)
                .orElseThrow(() -> new EmployerNotFoundException("Employer not found with ID: " + employerId));

            job.setEmployer(employer);
            Job savedJob = jobRepo.save(job);

            NotificationRequest request = new NotificationRequest();
            request.setTo(job.getContactEmail());
            request.setSubject("Job Posted Successfully");
            request.setBody("Your job '" + job.getJobTitle() + "' has been posted.");
            notificationClient.sendEmail(request);

            return savedJob;

        } catch (EmployerNotFoundException | IllegalArgumentException ex) {
            throw ex; 
        } catch (Exception ex) {
            throw new RuntimeException("Failed to post job: " + ex.getMessage(), ex);
        }
    }



//    public Job editJob(Long jobId, Job updatedJob) {
//        Job job = jobRepo.findById(jobId).orElseThrow();
//        // update fields
//        return jobRepo.save(job);
//    }
    
    @Override
    public Job editJob(Long jobId, Job updatedJob) {
        try {
            Job job = jobRepo.findById(jobId)
                .orElseThrow(() -> new JobNotFoundException("Job not found with ID: " + jobId));

            // Update fields
            job.setJobTitle(updatedJob.getJobTitle());
            job.setDescription(updatedJob.getDescription());
            job.setLocation(updatedJob.getLocation());
            job.setExperience(updatedJob.getExperience());
            job.setSalary(updatedJob.getSalary());
            job.setNoticePeriod(updatedJob.getNoticePeriod());
            job.setContactEmail(updatedJob.getContactEmail());
            job.setStatus(updatedJob.getStatus());

            return jobRepo.save(job);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to edit job: " + ex.getMessage(), ex);
        }
    }


//    public void deleteJob(Long jobId) {
//        jobRepo.deleteById(jobId);
//    }
    
    @Override
    public void deleteJob(Long jobId) {
        if (!jobRepo.existsById(jobId)) {
            throw new JobNotFoundException("Job not found with ID: " + jobId);
        }
        jobRepo.deleteById(jobId);
    }


    public List<Job> getJobsByEmployer(Long employerId) {
        return jobRepo.findByEmployerId(employerId);
    }
}

