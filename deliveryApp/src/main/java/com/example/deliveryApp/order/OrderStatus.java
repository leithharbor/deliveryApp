package com.example.deliveryApp.order;

public enum OrderStatus {
	PENDING,        // 대기 중
	ACCEPTED,       // 주문 수락
	REJECTED,       // 주문 거절
	IN_PROGRESS,    // 조리 중
	DELIVERING,     // 배달 중
	DELIVERED,      // 배달 완료
	CANCELLED;    // 주문 취소
}
