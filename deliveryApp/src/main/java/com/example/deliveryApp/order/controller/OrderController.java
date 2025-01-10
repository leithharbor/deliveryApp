package com.example.deliveryApp.order.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.deliveryApp.order.dto.OrderCancleResponseDto;
import com.example.deliveryApp.order.dto.OrderCreateRequestDto;
import com.example.deliveryApp.order.dto.OrderCreateResponseDto;
import com.example.deliveryApp.order.dto.OrderDetailResponseDto;
import com.example.deliveryApp.order.dto.OrderStatusChangeRequestDto;
import com.example.deliveryApp.order.dto.OrderStatusChangeResponseDto;
import com.example.deliveryApp.order.service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	//주문 생성
	@PostMapping
	public ResponseEntity<OrderCreateResponseDto> createOrder (@RequestBody OrderCreateRequestDto requestDto) {

		OrderCreateResponseDto orderCreateResponseDto = orderService.createOrder(requestDto);

		return new ResponseEntity<>(orderCreateResponseDto,HttpStatus.OK);
	}

}
