package com.employer.service.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.employer.service.entity.ErrorResponse;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEmployerNotFound(EmployerNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), "Employer not found"), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(JobNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleJobNotFound(JobNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), "Job not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentials(InvalidCredentialsException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), "Invalid login attempt"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), "Invalid argument"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointer(NullPointerException ex) {
        return new ResponseEntity<>(new ErrorResponse("Null value encountered", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorResponse> handleIOException(IOException ex) {
        return new ResponseEntity<>(new ErrorResponse("I/O error occurred", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(CustomCheckedException.class)
    public ResponseEntity<ErrorResponse> handleCustomChecked(CustomCheckedException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), "Checked exception occurred"), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        return new ResponseEntity<>(new ErrorResponse("Unexpected error", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
	