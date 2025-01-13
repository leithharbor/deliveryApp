package com.example.deliveryApp.user.exception;

import com.example.deliveryApp.common.exception.BaseException;
import com.example.deliveryApp.common.exception.ResponseCode;

//비밀번호 불 일치 예외
public class PasswordAuthFailException extends BaseException {
    public PasswordAuthFailException() {
        super(ResponseCode.PASSWORD_MISMATCH);
    }
}
