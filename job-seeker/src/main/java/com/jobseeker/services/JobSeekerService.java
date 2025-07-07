package com.jobseeker.services;

import java.util.List;

import com.jobseeker.entity.Job;
import com.jobseeker.entity.JobSeeker;

public interface JobSeekerService {
    JobSeeker register(JobSeeker jobSeeker);
    JobSeeker login(String username, String password);
    List<Job> searchJobs(String skill, String company);
    Job applyForJob(Long jobId);

}

