package com.example.deliveryApp.order.dto;

import java.time.LocalDateTime;

import com.example.deliveryApp.order.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderDetailResponseDto {

	private Long orderId;
	private String meneName;
	private OrderStatus orderStatus;
	private int totalPaymentprice;
	private LocalDateTime orderedAt;

}
