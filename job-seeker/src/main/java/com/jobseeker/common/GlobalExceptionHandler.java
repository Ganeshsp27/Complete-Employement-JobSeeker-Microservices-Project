package com.jobseeker.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<Object> handleUserAlreadyExists(UserAlreadyExistsException ex) {
	    Map<String, Object> errorDetails = new HashMap<>();
	    errorDetails.put("timestamp", LocalDateTime.now());
	    errorDetails.put("message", ex.getMessage());
	    errorDetails.put("status", HttpStatus.CONFLICT.value());
	    return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<Object> handleInvalidCredentials(InvalidCredentialsException ex) {
	    Map<String, Object> errorDetails = new HashMap<>();
	    errorDetails.put("timestamp", LocalDateTime.now());
	    errorDetails.put("message", ex.getMessage());
	    errorDetails.put("status", HttpStatus.UNAUTHORIZED.value());
	    return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(JobNotFoundException.class)
	public ResponseEntity<Object> handleJobNotFound(JobNotFoundException ex) {
	    Map<String, Object> errorDetails = new HashMap<>();
	    errorDetails.put("timestamp", LocalDateTime.now());
	    errorDetails.put("message", ex.getMessage());
	    errorDetails.put("status", HttpStatus.NOT_FOUND.value());
	    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

    
 

}
