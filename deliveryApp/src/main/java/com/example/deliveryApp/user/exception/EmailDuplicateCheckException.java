package com.example.deliveryApp.user.exception;

import com.example.deliveryApp.common.exception.BaseException;
import com.example.deliveryApp.common.exception.ResponseCode;

public class EmailDuplicateCheckException extends BaseException {
    public EmailDuplicateCheckException() {
        super(ResponseCode.EMAIL_ALREADY_EXISTS);
    }
}
