package com.example.deliveryApp.user.exception;

import com.example.deliveryApp.common.exception.BaseException;
import com.example.deliveryApp.common.exception.ResponseCode;

public class UserDeleteAlreadyException extends BaseException {
    public UserDeleteAlreadyException() {
        super(ResponseCode.USER_ALREADY_DELETE);
    }
}
