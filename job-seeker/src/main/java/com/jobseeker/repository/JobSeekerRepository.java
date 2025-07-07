package com.jobseeker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobseeker.entity.JobSeeker;


public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {
 JobSeeker findByUsernameAndPassword(String username, String password);
}


