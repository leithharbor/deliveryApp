package com.example.deliveryApp.order.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderCreateResponseDto {

	private String message;
	private String storeName;
	private Long orderId;
	private int totalPaymentPrice;
	private LocalDateTime orderedAt;
}