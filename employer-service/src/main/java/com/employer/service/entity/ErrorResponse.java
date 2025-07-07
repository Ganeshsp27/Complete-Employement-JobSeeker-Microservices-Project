package com.employer.service.entity;


import java.time.LocalDateTime;

public class ErrorResponse {
    private String message;
    private String timestamp;
    private String details;

    public ErrorResponse(String message, String details) {
        this.message = message;
        this.timestamp = LocalDateTime.now().toString();
        this.details = details;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

   
}

