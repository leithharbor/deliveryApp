package com.example.deliveryApp.Review.service;

import com.example.deliveryApp.Review.dto.ReviewRequestDto;
import com.example.deliveryApp.Review.dto.ReviewResponseDto;
import com.example.deliveryApp.Review.repository.ReviewRepository;
import com.example.deliveryApp.entity.Order;
import com.example.deliveryApp.entity.Review;
import com.example.deliveryApp.order.OrderStatus;
import com.example.deliveryApp.order.repository.OrderRepository;
import com.example.deliveryApp.store.repository.StoreRepository;
import com.example.deliveryApp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    //리뷰 생성
    public ReviewResponseDto createReview(ReviewRequestDto reviewRequestDto) {

        //주문 확인
        Order order = orderRepository.findById(reviewRequestDto.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("주문 내역이 없습니다."));

        //주문 상태 -> 배달 완료만 가능
        if (!order.getOrderStatus().equals(OrderStatus.DELIVERED)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "배달 완료인 주문만 리뷰 작성할 수 있습니다.");
        }

        //리뷰 저장
        Review review = new Review(order,
                reviewRequestDto.getRating(),
                reviewRequestDto.getContents());

        reviewRepository.save(review);
        return new ReviewResponseDto(review);
    }

}