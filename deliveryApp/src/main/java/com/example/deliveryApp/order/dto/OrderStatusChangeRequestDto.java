package com.example.deliveryApp.order.dto;

import com.example.deliveryApp.order.OrderStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class OrderStatusChangeRequestDto {

	@NotNull(message = "변경하려는 주문 상태를 입력해주세요.")
	private OrderStatus orderStatus;
	private String reason;

	public OrderStatusChangeRequestDto (OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public OrderStatusChangeRequestDto (OrderStatus orderStatus, String reason) {
		this.orderStatus = orderStatus;
		this.reason = reason;
	}

}
