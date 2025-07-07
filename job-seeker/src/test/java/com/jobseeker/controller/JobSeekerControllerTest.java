package com.jobseeker.controller;

import com.jobseeker.entity.Job;
import com.jobseeker.entity.JobSeeker;
import com.jobseeker.services.JobSeekerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(JobSeekerController.class)
class JobSeekerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobSeekerService jobSeekerService;

    @Test
    void testRegister() throws Exception {
        JobSeeker seeker = new JobSeeker();
        seeker.setUsername("john");
        Mockito.when(jobSeekerService.register(Mockito.any())).thenReturn(seeker);

        mockMvc.perform(post("/jobseeker/register")
        		.contentType(MediaType.APPLICATION_JSON)
                               .content("{\"username\":\"john\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testLogin() throws Exception {
        JobSeeker seeker = new JobSeeker();
        Mockito.when(jobSeekerService.login("john", "pass")).thenReturn(seeker);

        mockMvc.perform(post("/jobseeker/login")
                .param("username", "john")
                .param("password", "pass"))
                .andExpect(status().isOk());
    }

    @Test
    void testSearchJobs() throws Exception {
        Job job = new Job();
        Mockito.when(jobSeekerService.searchJobs("Java", "Pune")).thenReturn(List.of(job));

        mockMvc.perform(get("/jobseeker/search")
                .param("skill", "Java")
                .param("company", "Pune"))
                .andExpect(status().isOk());
    }

    @Test
    void testApplyForJob() throws Exception {
        Job job = new Job();
        Mockito.when(jobSeekerService.applyForJob(1L)).thenReturn(job);

        mockMvc.perform(post("/jobseeker/apply")
                .param("jobId", "1"))
                .andExpect(status().isOk());
    }
}
