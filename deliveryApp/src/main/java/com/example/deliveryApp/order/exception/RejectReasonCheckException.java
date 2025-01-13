package com.example.deliveryApp.order.exception;

import com.example.deliveryApp.common.exception.BaseException;
import com.example.deliveryApp.common.exception.ResponseCode;

//거절 사유 확인 예외
public class RejectReasonCheckException extends BaseException {
    public RejectReasonCheckException() {
        super(ResponseCode.REASON_NULL_CHECK);
    }
}
