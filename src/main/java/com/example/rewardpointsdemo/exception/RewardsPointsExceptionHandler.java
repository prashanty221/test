package com.example.rewardpointsdemo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class RewardsPointsExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity handleBindException(BindException ex, HttpServletRequest request) {
        //Handling Bad request exception
        log.error("Error occured while processing request - {}", ex.getMessage());

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, request.getRequestURI(), "Validation Error", ex);
        List<ApiSubError> subErrors = new ArrayList<>();
        for (FieldError fieldError : ex.getFieldErrors()) {
            ApiSubError apiSubError = new ApiValidationError(fieldError.getObjectName(), fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage());
            subErrors.add(apiSubError);
        }

        apiError.setSubErrors(subErrors);
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        //Handling constraint violation exception
        log.error("Error occured while processing request - {}", ex.getMessage());

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, request.getRequestURI(), "Validation Error", ex);
        List<ApiSubError> subErrors = new ArrayList<>();
        for (ConstraintViolation violation : ex.getConstraintViolations()) {
            ApiSubError apiSubError = new ApiValidationError(violation.getPropertyPath().toString(), violation.getPropertyPath().toString(), violation.getInvalidValue(), violation.getMessage());
            subErrors.add(apiSubError);
        }

        apiError.setSubErrors(subErrors);
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception ex, HttpServletRequest request) {
        //Handling Any Other exception
        log.error("Error occured while processing request - {}", ex.getMessage());

        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, request.getRequestURI(), "Error", ex);
        return ResponseEntity.internalServerError().body(apiError);
    }
}
