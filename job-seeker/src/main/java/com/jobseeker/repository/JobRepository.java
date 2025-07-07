package com.jobseeker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobseeker.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
   // List<Job> findBySkillSetContainingOrCompanyContaining(String skillSet, String company);
    List<Job> findByJobTitleContainingOrLocationContaining(String jobTitle, String location);

    
}

