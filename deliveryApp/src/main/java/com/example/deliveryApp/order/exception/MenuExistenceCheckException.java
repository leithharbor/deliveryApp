package com.example.deliveryApp.order.exception;

import com.example.deliveryApp.common.exception.BaseException;
import com.example.deliveryApp.common.exception.ResponseCode;

// 메뉴 존재 확인 예외
public class MenuExistenceCheckException extends BaseException {
    public MenuExistenceCheckException() {
        super(ResponseCode.MENU_NOT_FOUND);
    }
}
