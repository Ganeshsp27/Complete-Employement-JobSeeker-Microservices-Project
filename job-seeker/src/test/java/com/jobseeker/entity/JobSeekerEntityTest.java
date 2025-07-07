package com.jobseeker.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JobSeekerEntityTest {

    @Test
    void testJobSeekerFields() {
        JobSeeker seeker = new JobSeeker();
        seeker.setUsername("john123");
        seeker.setPassword("securePass");
        seeker.setName("John Doe");
        seeker.setAddress("123 Street");
        seeker.setContactNo("9876543210");
        seeker.setEmail("john@example.com");
        seeker.setSkillSet("Java, Spring Boot");

        assertEquals("john123", seeker.getUsername());
        assertEquals("securePass", seeker.getPassword());
        assertEquals("John Doe", seeker.getName());
        assertEquals("123 Street", seeker.getAddress());
        assertEquals("9876543210", seeker.getContactNo());
        assertEquals("john@example.com", seeker.getEmail());
        assertEquals("Java, Spring Boot", seeker.getSkillSet());
    }
}
