package com.example.deliveryApp.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseCode {

    PASSWORD_MISMATCH(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다. 다시 입력해주세요."),
    URL_NOT_FOUND(HttpStatus.NOT_FOUND, "잘못된 경로입니다."),
    EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 사용중인 이메일입니다."),
    USER_ALREADY_DELETE(HttpStatus.BAD_REQUEST, "이미 탈퇴 처리 된 회원입니다."),
    PASSWORD_SAME_AS_BEFORE(HttpStatus.BAD_REQUEST, "바꾸려는 비밀번호가 이전과 동일하거나, 입력한 비밀번호가 서로 다릅니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "회원을 찾을 수 없습니다."),
    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "입력하신 아이디를 찾을 수 없습니다. 다시 확인해주세요."),
    ID_MISMATCH(HttpStatus.UNAUTHORIZED, "권한이 존재하지 않습니다."),
    MENU_NOT_FOUND(HttpStatus.NOT_FOUND,"메뉴를 찾을 수 없습니다."),
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND,"주문번호를 찾을 수 없습니다."),
    ORDER_AMOUNT_UNFULFILLED(HttpStatus.FORBIDDEN,"총 주문 금액이 최소 주문 금액보다 적습니다."),
    BUSINESS_HOURS_INCONSISTENCY(HttpStatus.FORBIDDEN,"지금은 영업시간이 아닙니다."),
    ORDERSTATUS_SAME_CURRENT(HttpStatus.BAD_REQUEST,"변경하려는 주문상태가 현재와 같습니다."),
    ORDERSTATUS_CHANGE_IMPOSSIBLE(HttpStatus.BAD_REQUEST,"해당 주문상태로 변경할 수 없습니다."),
    REASON_NULL_CHECK(HttpStatus.BAD_REQUEST,"주문 거절 시, 거절 사유는 필수 입력해야 합니다."),
    ORDER_ALREADY_CANCLE(HttpStatus.BAD_REQUEST,"이미 취소 처리 된 주문입니다.");

    private final HttpStatus status;
    private final String message;

    ResponseCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
