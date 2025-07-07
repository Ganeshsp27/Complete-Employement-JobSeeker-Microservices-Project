package com.jobseeker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class JobSeekerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobSeekerApplication.class, args);
	}

}
