package com.employer.service.services;

import java.util.List;

import com.employer.service.entity.Employer;
import com.employer.service.entity.Job;

public interface EmployerService {
    Employer register(Employer employer);
    Employer login(String username, String password);
    Job postJob(Long employerId, Job job);
    Job editJob(Long jobId, Job job);
    void deleteJob(Long jobId);
    List<Job> getJobsByEmployer(Long employerId);
}

