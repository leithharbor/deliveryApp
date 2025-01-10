package com.example.deliveryApp.order.service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.deliveryApp.entity.Menu;
import com.example.deliveryApp.entity.Order;
import com.example.deliveryApp.menu.MenuRepository;
import com.example.deliveryApp.order.dto.OrderCreateRequestDto;
import com.example.deliveryApp.order.dto.OrderCreateResponseDto;
import com.example.deliveryApp.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final MenuRepository menuRepository;

	//주문 생성 API
	public OrderCreateResponseDto createOrder(OrderCreateRequestDto requestDto) {

		//menu가 존재하는지 확인
		Menu menu = menuRepository.findById(requestDto.getMenuId())
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

		//store에 있는 menu가 맞는지 확인
		if (!menu.getStore().getId().equals(requestDto.getStoreId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "가게에서 해당 메뉴를 찾을 수 없습니다.");
		}

		//최소 주문 금액 불충족 403 Forbidden
		if (menu.getPrice() < menu.getStore().getDeliveryMinPrice()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN,
				"최소 주문 금액을 맞춰주세요. 최소 주문 금액 : " + menu.getStore().getDeliveryMinPrice());
		}

		//오픈-마감시간 오류 403 Forbidden
		//HH:mm - HH:mm 형태에서 시간을 분리
		String[] times = menu.getStore().getOpenClose().split("\\s*-\\s*");

		//분리한 String 타입 시간을 LocalDateTime 타입의 시간으로 바꿔주기
		LocalTime openTime = LocalTime.parse(times[0]);
		LocalTime closeTime = LocalTime.parse(times[1]);

		//현재 시간
		LocalTime now = LocalTime.now();

		//오픈시간 이전이거나, 클로즈시간 이후일 때 실행
		if (now.isBefore(openTime) || now.isAfter(closeTime)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN,
				"영업시간을 확인해주세요. 영업시간 : " + menu.getStore().getOpenClose());
		}

		//주문 생성
		Order order = new Order(menu, menu.getPrice());

		//생성 한 주문 저장
		orderRepository.save(order);

		return new OrderCreateResponseDto("요청이 정상적으로 처리되었습니다.", order.getTotalPaymentPrice(),order.getOrderedAt());
	}

}
