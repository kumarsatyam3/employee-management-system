package com.satyam.employee_management_system.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice 
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleResourceNotFoundException(
        ResourceNotFoundException exception , WebRequest webRequest){

            Map<String,Object> errorDetails = new HashMap<>();
            errorDetails.put("timeStamp",new Date());
            errorDetails.put("message", exception.getMessage());
            errorDetails.put("details", webRequest.getDescription(false));
            errorDetails.put("status", HttpStatus.NOT_FOUND.value());

            return new ResponseEntity<>(errorDetails , HttpStatus.NOT_FOUND);
        }
}
