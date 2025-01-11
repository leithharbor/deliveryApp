package com.example.deliveryApp.order.exception;

import com.example.deliveryApp.common.exception.BaseException;
import com.example.deliveryApp.common.exception.ResponseCode;

// 총 주문 금액 불충족 예외
public class TotalOrderAmountUnfulfilledException extends BaseException {
    public TotalOrderAmountUnfulfilledException() {
        super(ResponseCode.ORDER_AMOUNT_UNFULFILLED);
    }
}
