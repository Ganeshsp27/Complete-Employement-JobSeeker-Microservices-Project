package com.jobseeker.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig 
{
	@Bean
	public NewTopic javaGuidesTopic()
	{
		return TopicBuilder.name("jobApplicationTopic").build();
	}
}
