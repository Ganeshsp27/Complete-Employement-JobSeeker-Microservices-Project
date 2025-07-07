package com.jobseeker.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JobEntityTest {

    @Test
    void testJobFields() {
        Job job = new Job();
        job.setJobTitle("Developer");
        job.setLocation("Pune");
        job.setDescription("Java Developer");
        job.setExperience(3);
        job.setSalary(75000);
        job.setNoticePeriod(30);
        job.setContactEmail("hr@example.com");
        job.setStatus("Open");

        assertEquals("Developer", job.getJobTitle());
        assertEquals("Pune", job.getLocation());
        assertEquals("Java Developer", job.getDescription());
        assertEquals(3, job.getExperience());
        assertEquals(75000, job.getSalary());
        assertEquals(30, job.getNoticePeriod());
        assertEquals("hr@example.com", job.getContactEmail());
        assertEquals("Open", job.getStatus());
    }
}
