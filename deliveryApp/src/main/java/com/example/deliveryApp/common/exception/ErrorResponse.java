package com.example.deliveryApp.common.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private int status;
    private String error;
    private String message;

    public ErrorResponse (ResponseCode responseCode) {
        this.status = responseCode.getStatus().value();
        this.error = responseCode.getStatus().getReasonPhrase();
        this.message = responseCode.getMessage();
    }

}
