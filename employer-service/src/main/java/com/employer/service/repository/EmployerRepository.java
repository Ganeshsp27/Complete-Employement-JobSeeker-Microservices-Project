package com.employer.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employer.service.entity.Employer;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
	

Employer findByUsernameAndPassword(String username, String password);


}
