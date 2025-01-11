package com.example.deliveryApp.order.exception;

import com.example.deliveryApp.common.exception.BaseException;
import com.example.deliveryApp.common.exception.ResponseCode;

//주문 상태 동일 예외
public class OrderStatusSameException extends BaseException {
    public OrderStatusSameException() {
        super(ResponseCode.ORDERSTATUS_SAME_CURRENT);
    }
}
