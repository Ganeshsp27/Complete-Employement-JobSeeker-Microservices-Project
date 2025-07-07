package com.employer.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employer.service.entity.Employer;
import com.employer.service.entity.Job;
import com.employer.service.entity.NotificationRequest;
import com.employer.service.repository.NotificationClient;
import com.employer.service.services.EmployerService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/employer")
public class EmployerController {
    @Autowired private EmployerService employerService;
    
    @Autowired private NotificationClient notificationClient;


    @PostMapping("/register")
    public ResponseEntity<Employer> register(@RequestBody Employer employer) {
        return ResponseEntity.ok(employerService.register(employer));
    }

    @PostMapping("/login")
    public ResponseEntity<Employer> login(@RequestParam String username, @RequestParam String password) {
        return ResponseEntity.ok(employerService.login(username, password));
    }

    @PostMapping("/{id}/job")
    public ResponseEntity<Job> postJob(@PathVariable Long id, @RequestBody Job job) {
        return ResponseEntity.ok(employerService.postJob(id, job));
    }

    @PutMapping("/job/{jobId}")
    public ResponseEntity<Job> editJob(@PathVariable Long jobId, @RequestBody Job job) {
        return ResponseEntity.ok(employerService.editJob(jobId, job));
    }

    @DeleteMapping("/job/{jobId}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long jobId) {
        employerService.deleteJob(jobId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/jobs")
    public ResponseEntity<List<Job>> getJobs(@PathVariable Long id) {
        return ResponseEntity.ok(employerService.getJobsByEmployer(id));
    }
    

	@GetMapping("/call")
	@CircuitBreaker(name = "notification-service", fallbackMethod = "fallback")
	 public String callService() {

		NotificationRequest request = new NotificationRequest();
		request.setTo("test@example.com");
		request.setSubject("Circuit Breaker Test");
		request.setBody("This is a test email from /call endpoint.");


		String response = notificationClient.sendEmail(request);
		return "Notification sent: " + response;

	}
	
	public String fallback(Throwable t) {
		return "Fallback response: Notification service is unavailable.";
	}

    
    
    
    
}

