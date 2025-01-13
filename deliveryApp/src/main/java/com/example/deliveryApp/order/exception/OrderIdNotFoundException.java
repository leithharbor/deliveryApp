package com.example.deliveryApp.order.exception;

import com.example.deliveryApp.common.exception.BaseException;
import com.example.deliveryApp.common.exception.ResponseCode;

// 주문번호 확인 예외
public class OrderIdNotFoundException extends BaseException {
    public OrderIdNotFoundException() {
        super(ResponseCode.ORDER_NOT_FOUND);
    }
}
