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
    ID_MISMATCH(HttpStatus.UNAUTHORIZED, "권한이 존재하지 않습니다.");


    private final HttpStatus status;
    private final String message;

    ResponseCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
