package com.example.deliveryApp.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> HandleException(BaseException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getResponseCode());
        return ResponseEntity.status(e.getResponseCode().getStatus()).body(errorResponse);
    }
}
