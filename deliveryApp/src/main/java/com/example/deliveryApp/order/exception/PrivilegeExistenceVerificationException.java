package com.example.deliveryApp.order.exception;

import com.example.deliveryApp.common.exception.BaseException;
import com.example.deliveryApp.common.exception.ResponseCode;

//권한 존재 확인 예외
public class PrivilegeExistenceVerificationException extends BaseException {
    public PrivilegeExistenceVerificationException() {
        super(ResponseCode.ID_MISMATCH);
    }
}
