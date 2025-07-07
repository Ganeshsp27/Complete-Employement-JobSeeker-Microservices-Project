package com.employer.service.entity;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmployerTest {

    @Test
    void testGettersAndSetters() {
        Employer employer = new Employer();
        employer.setId(1L);
        employer.setOrganizationName("Tech Corp");
        employer.setAddress("123 Street");
        employer.setContactNo("1234567890");
        employer.setEmail("hr@techcorp.com");
        employer.setUsername("techuser");
        employer.setPassword("securepass");

        assertEquals(1L, employer.getId());
        assertEquals("Tech Corp", employer.getOrganizationName());
        assertEquals("123 Street", employer.getAddress());
        assertEquals("1234567890", employer.getContactNo());
        assertEquals("hr@techcorp.com", employer.getEmail());
        assertEquals("techuser", employer.getUsername());
        assertEquals("securepass", employer.getPassword());
    }
}
