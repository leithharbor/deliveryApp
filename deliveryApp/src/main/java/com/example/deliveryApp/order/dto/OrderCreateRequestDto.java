package com.example.deliveryApp.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderCreateRequestDto {

	private Long storeId;
	private Long menuId;
}