package com.example.deliveryApp.order.dto;

import com.example.deliveryApp.order.OrderStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderStatusChangeResponseDto {
	private String message;
	private OrderStatus orderStatus;
	private String reason;

	public OrderStatusChangeResponseDto (String reason) {
		this.message = "요청이 정상적으로 처리되었습니다.";
		this.reason = reason;
	}

	public OrderStatusChangeResponseDto (OrderStatus orderStatus) {
		this.message = "요청이 정상적으로 처리되었습니다.";
		this.orderStatus = orderStatus;
	}

}
