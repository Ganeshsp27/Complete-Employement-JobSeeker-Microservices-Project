package com.jobseeker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobseeker.entity.Job;
import com.jobseeker.entity.JobSeeker;
import com.jobseeker.services.JobSeekerService;

@RestController
@RequestMapping("/jobseeker")
public class JobSeekerController {

    @Autowired
    private JobSeekerService jobSeekerService;

    @PostMapping("/register")
    public ResponseEntity<JobSeeker> register(@RequestBody JobSeeker jobSeeker) {
        return ResponseEntity.ok(jobSeekerService.register(jobSeeker));
    }

    @PostMapping("/login")
    public ResponseEntity<JobSeeker> login(@RequestParam String username, @RequestParam String password) {
        return ResponseEntity.ok(jobSeekerService.login(username, password));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Job>> searchJobs(@RequestParam String skill, @RequestParam String company) {
        return ResponseEntity.ok(jobSeekerService.searchJobs(skill, company));
    }
    
    @PostMapping("/apply")
    public ResponseEntity<Job> applyForJob(@RequestParam Long jobId) {
        return ResponseEntity.ok(jobSeekerService.applyForJob(jobId));
    }

}

