package com.example.deliveryApp.order;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public enum OrderStatus {
	PENDING,        // 대기 중
	ACCEPTED,       // 주문 수락
	REJECTED,       // 주문 거절
	IN_PROGRESS,    // 조리 중
	DELIVERING,     // 배달 중
	DELIVERED,      // 배달 완료
	CANCELLED;    // 주문 취소

	//　주문　상태　확인
	public void orderStatusManagement (OrderStatus nextStatus) {

		if ((this == DELIVERING || this == DELIVERED) && (nextStatus == PENDING || nextStatus == ACCEPTED || nextStatus == REJECTED
			|| nextStatus == IN_PROGRESS)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this + "에서 " + nextStatus + " 로 변경할 수 없습니다.");
		}

		if(this == DELIVERED && nextStatus == DELIVERING) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this + "에서 " + nextStatus + " 로 변경할 수 없습니다.");
		}
	}
}
