package com.employer.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employer.service.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
	
	List<Job> findByEmployerId(Long employerId);

}
