package com.example.deliveryApp.order.exception;

import com.example.deliveryApp.common.exception.BaseException;
import com.example.deliveryApp.common.exception.ResponseCode;

//유저 존재 확인 예외
public class UserExistenceCheckException extends BaseException {
    public UserExistenceCheckException() {
        super(ResponseCode.USER_NOT_FOUND);
    }
}
