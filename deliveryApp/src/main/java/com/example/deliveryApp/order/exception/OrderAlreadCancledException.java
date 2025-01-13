package com.example.deliveryApp.order.exception;

import com.example.deliveryApp.common.exception.BaseException;
import com.example.deliveryApp.common.exception.ResponseCode;

public class OrderAlreadCancledException extends BaseException {
    public OrderAlreadCancledException() {
        super(ResponseCode.ORDER_ALREADY_CANCLE);
    }
}
