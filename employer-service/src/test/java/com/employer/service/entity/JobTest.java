package com.employer.service.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JobTest {

    @Test
    void testGettersAndSetters() {
        Job job = new Job();
        job.setId(1L);
        job.setJobTitle("Software Engineer");
        job.setLocation("Remote");
        job.setDescription("Develop and maintain software.");
        job.setExperience(3);
        job.setSalary(100000);
        job.setNoticePeriod(60);
        job.setContactEmail("hr@example.com");
        job.setStatus("Open");

        assertEquals(1L, job.getId());
        assertEquals("Software Engineer", job.getJobTitle());
        assertEquals("Remote", job.getLocation());
        assertEquals("Develop and maintain software.", job.getDescription());
        assertEquals(3, job.getExperience());
        assertEquals(100000, job.getSalary());
        assertEquals(60, job.getNoticePeriod());
        assertEquals("hr@example.com", job.getContactEmail());
        assertEquals("Open", job.getStatus());
    }
}
