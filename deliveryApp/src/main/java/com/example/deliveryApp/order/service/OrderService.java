package com.example.deliveryApp.order.service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import com.example.deliveryApp.entity.User;
import com.example.deliveryApp.entity.UserType;
import com.example.deliveryApp.order.exception.*;
import com.example.deliveryApp.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.deliveryApp.entity.Menu;
import com.example.deliveryApp.entity.Order;
import com.example.deliveryApp.menu.repository.MenuRepository;
import com.example.deliveryApp.order.OrderStatus;
import com.example.deliveryApp.order.dto.OrderCancleResponseDto;
import com.example.deliveryApp.order.dto.OrderCreateRequestDto;
import com.example.deliveryApp.order.dto.OrderCreateResponseDto;
import com.example.deliveryApp.order.dto.OrderDetailResponseDto;
import com.example.deliveryApp.order.dto.OrderStatusChangeRequestDto;
import com.example.deliveryApp.order.dto.OrderStatusChangeResponseDto;
import com.example.deliveryApp.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final MenuRepository menuRepository;
	private final UserRepository userRepository;

	//주문 생성 API
	public OrderCreateResponseDto createOrder(OrderCreateRequestDto requestDto, HttpSession session) {

		//로그인 확인
		if(session == null) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"로그인하지 않았습니다. 로그인 먼저 진행해주세요.");
		}

		//세션에서 userId 가져오기
		Long userId = (Long) session.getAttribute("loginUserId");

		//user Id로 user 조회
		User sessionUser = userRepository.findById(userId)
				.orElseThrow(() -> new UserExistenceCheckException());

		//권한 존재 확인(유저)
		if (sessionUser.getUserType() == UserType.OWNER) {
			throw new PrivilegeExistenceVerificationException();
		}

		//menu가 존재하는지 확인
		Menu menu = menuRepository.findById(requestDto.getMenuId())
				.orElseThrow(() -> new MenuExistenceCheckException());

		//store에 있는 menu가 맞는지 확인
		if (!menu.getStore().getId().equals(requestDto.getStoreId())) {
			throw new MenuExistenceCheckException();
		}

		//최소 주문 금액 불충족 403 Forbidden
		if (menu.getPrice() < menu.getStore().getDeliveryMinPrice()) {
			throw new TotalOrderAmountUnfulfilledException();
		}

		//오픈-마감시간 오류 403 Forbidden
		//HH:mm - HH:mm 형태에서 시간을 분리
		String[] times = menu.getStore().getOpenCloseTime().split("\\s*-\\s*");

		//분리한 String 타입 시간을 LocalDateTime 타입의 시간으로 바꿔주기
		LocalTime openTime = LocalTime.parse(times[0]);
		LocalTime closeTime = LocalTime.parse(times[1]);

		//현재 시간
		LocalTime now = LocalTime.now();

		//오픈시간 이전이거나, 클로즈시간 이후일 때 실행
		if (now.isBefore(openTime) || now.isAfter(closeTime)) {
			throw new BusinessHoursDiscrepancyException();
		}

		//주문 생성
		Order order = new Order(menu, sessionUser, menu.getPrice());

		//생성 한 주문 저장
		orderRepository.save(order);

		return new OrderCreateResponseDto("주문이 완료되었습니다.", order.getTotalPaymentPrice(),order.getOrderedAt());
	}

	//주문 상태 변경 API
	public OrderStatusChangeResponseDto changeOrderStatus(Long orderId, OrderStatusChangeRequestDto requestDto) {

		//id로 order 조회
		Order order = orderRepository.findById(orderId)
			.orElseThrow(() -> new MenuExistenceCheckException());

		//현재 주문상태와 변경하려는 주문상태가 같은지 확인
		if(order.getOrderStatus() == requestDto.getOrderStatus()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"변경하려는 주문상태가 현재와 동일합니다.");
		}

		//주문 상태 변경 확인

		order.getOrderStatus().orderStatusManagement(requestDto.getOrderStatus());

		//상태 변경
		order.changeOrderStatus(requestDto.getOrderStatus());

		// 변경 후 DB에 저장
		orderRepository.save(order);

		//주문상태를 REJECTED로 변경하는 경우 reason 포함 응답 반환
		if (requestDto.getOrderStatus() == OrderStatus.REJECTED) {
			if (requestDto.getReason() == null) {
				throw new RejectReasonCheckException();
			}
			return new OrderStatusChangeResponseDto(requestDto.getReason());
		}

		// REJECTED를 제외한 다른 상태변경 응답
		return new OrderStatusChangeResponseDto(order.getOrderStatus());
	}

	//주문 내역 조회 API
	public List<OrderDetailResponseDto> lookupOrders() {

		List<Order> orderList = orderRepository.findAll();

		List<OrderDetailResponseDto> responseDtoList = orderList.stream().map(order -> new OrderDetailResponseDto(
				order.getId(),
				order.getMenu().getMenuName(),
				order.getOrderStatus(),
				order.getTotalPaymentPrice(),
				order.getOrderedAt()))
			.collect(Collectors.toList());

		return responseDtoList;
	}

	//주문 취소
	public OrderCancleResponseDto orderCancel(Long orderId) {

		// 주문 id 확인
		Order foundOrder = orderRepository.findById(orderId)
			.orElseThrow(() -> new MenuExistenceCheckException());

		// 이미 취소처리 된 주문 확인
		if(foundOrder.getOrderStatus() == OrderStatus.CANCELLED) {
			throw new OrderAlreadCancledException();
		}

		foundOrder.changeOrderStatus(OrderStatus.CANCELLED);

		orderRepository.save(foundOrder);

		return new OrderCancleResponseDto("주문이 취소되었습니다.");
	}

}
