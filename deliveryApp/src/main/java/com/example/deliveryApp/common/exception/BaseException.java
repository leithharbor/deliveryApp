package com.example.deliveryApp.common.exception;

import lombok.Getter;

@Getter
//기초 예외
public class BaseException extends RuntimeException {
    private final ResponseCode responseCode;

    public BaseException(String message, ResponseCode responseCode) {
        super(message);
        this.responseCode = responseCode;
    }
    public BaseException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }
}
