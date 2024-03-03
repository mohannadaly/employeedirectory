package com.mohanad.springpractice.exceptions.handlers;

import com.mohanad.springpractice.exceptions.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler {
    public record EmployeeResponse(Integer status, String message, Long time) {
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeResponse> reponseEntity(EmployeeNotFoundException exc) {
        EmployeeResponse employeeResponse = new EmployeeResponse(
                HttpStatus.NOT_FOUND.value(), exc.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(employeeResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeResponse> reponseEntity(Exception exc) {
        EmployeeResponse employeeResponse = new EmployeeResponse(
                HttpStatus.BAD_REQUEST.value(), exc.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(employeeResponse, HttpStatus.BAD_REQUEST);
    }
}
