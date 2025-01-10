package com.example.deliveryApp.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ResponseCode> HandleException(BaseException e) {
        return ResponseEntity.status(e.getResponseCode().getStatus()).body(e.getResponseCode());
    }
}
