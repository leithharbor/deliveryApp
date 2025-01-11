package com.example.deliveryApp.order.exception;

import com.example.deliveryApp.common.exception.BaseException;
import com.example.deliveryApp.common.exception.ResponseCode;

//주문 상태 변경 불가능 예외
public class Cannotchangeorderstatusexception extends BaseException {
    public Cannotchangeorderstatusexception() {
        super(ResponseCode.ORDERSTATUS_CHANGE_IMPOSSIBLE);
    }
}
