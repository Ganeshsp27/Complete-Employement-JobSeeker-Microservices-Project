package com.jobseeker.servicesImpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobseeker.common.InvalidCredentialsException;
import com.jobseeker.common.JobNotFoundException;
import com.jobseeker.common.UserAlreadyExistsException;
import com.jobseeker.entity.Job;
import com.jobseeker.entity.JobSeeker;
import com.jobseeker.kafkaImpl.JobApplicationProducer;
import com.jobseeker.repository.JobRepository;
import com.jobseeker.repository.JobSeekerRepository;
import com.jobseeker.services.JobSeekerService;

@Service
public class JobSeekerServiceImpl implements JobSeekerService {

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    private JobRepository jobRepository;
    

	@Autowired
	private JobApplicationProducer jobApplicationProducer;


//    @Override
//    public JobSeeker register(JobSeeker jobSeeker) {
//        return jobSeekerRepository.save(jobSeeker);
//    }
//
//    @Override
//    public JobSeeker login(String username, String password) {
//        return jobSeekerRepository.findByUsernameAndPassword(username, password);
//    }

    @Override
    public List<Job> searchJobs(String skill, String company) {
        return jobRepository.findByJobTitleContainingOrLocationContaining(skill, company);
    }
    
//    @Override
//    public Job applyForJob(Long jobId) {
//        Job job = jobRepository.findById(jobId)
//            .orElseThrow(() -> new JobNotFoundException("Job not found with ID: " + jobId));
//        job.setStatus("Applied");
//        jobApplicationProducer.sendApplicationNotification("Job ID " + jobId + " has been applied for.");
//        return jobRepository.save(job);
//    }
    
    @Override
    public JobSeeker register(JobSeeker jobSeeker) {
        if (jobSeekerRepository.findByUsernameAndPassword(jobSeeker.getUsername(), jobSeeker.getPassword()) != null) {
            throw new UserAlreadyExistsException("User already registered with this username.");
        }
        return jobSeekerRepository.save(jobSeeker);
    }

    @Override
    public JobSeeker login(String username, String password) {
        JobSeeker seeker = jobSeekerRepository.findByUsernameAndPassword(username, password);
        if (seeker == null) {
            throw new InvalidCredentialsException("Invalid username or password.");
        }
        return seeker;
    }

    @Override
    public Job applyForJob(Long jobId) {
        Job job = jobRepository.findById(jobId)
            .orElseThrow(() -> new JobNotFoundException("Job not found with ID: " + jobId));
        job.setStatus("Applied");
        jobApplicationProducer.sendApplicationNotification("Job ID " + jobId + " has been applied for.");
        return jobRepository.save(job);
    }




}

