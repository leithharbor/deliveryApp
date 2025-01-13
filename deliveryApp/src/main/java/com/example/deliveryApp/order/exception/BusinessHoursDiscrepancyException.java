package com.example.deliveryApp.order.exception;

import com.example.deliveryApp.common.exception.BaseException;
import com.example.deliveryApp.common.exception.ResponseCode;

// 영업시간 불일치 예외
public class BusinessHoursDiscrepancyException extends BaseException {
    public BusinessHoursDiscrepancyException() {
        super(ResponseCode.BUSINESS_HOURS_INCONSISTENCY);
    }
}
