package com.example.deliveryApp.Review.service;

import com.example.deliveryApp.Review.dto.ReviewRequestDto;
import com.example.deliveryApp.Review.dto.ReviewResponseDto;
import com.example.deliveryApp.Review.repository.ReviewRepository;
import com.example.deliveryApp.entity.Order;
import com.example.deliveryApp.entity.Review;
import com.example.deliveryApp.entity.Store;
import com.example.deliveryApp.entity.User;
import com.example.deliveryApp.order.OrderStatus;
import com.example.deliveryApp.order.repository.OrderRepository;
import com.example.deliveryApp.store.repository.StoreRepository;
import com.example.deliveryApp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    //리뷰 생성
    @Transactional
    public ReviewResponseDto createReview(ReviewRequestDto reviewRequestDto) {

        //주문 확인
        Order order = orderRepository.findById(reviewRequestDto.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("주문 내역이 없습니다."));

        //주문 상태 -> 배달 완료만 가능
        if(!order.getOrderStatus().equals(OrderStatus.DELIVERED)){
            throw new IllegalArgumentException("배달 완료인 주문만 리뷰 작성할 수 있습니다.");
        }

        // 이미 작성된 리뷰가 있는지
        if (reviewRepository.existsByOrder(order)) {
            throw new IllegalArgumentException("이미 리뷰가 있습니다.");
        }

        // Store 조회
        Long storeId = order.getMenu().getStore().getId();
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("가게를 찾을 수 없습니다."));

        // User 조회
        User user = userRepository.findById(reviewRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        //리뷰 저장
        Review review = new Review(order, store, user,
                reviewRequestDto.getRating(),
                reviewRequestDto.getContents());

        reviewRepository.save(review);
        return new ReviewResponseDto(review);
    }

    // 리뷰 조회 (단건조회X, 주문한 스토어에서만 전체조회)
    /**
     * @Param storeId
     * @return
     */
    public List<ReviewResponseDto> GetAllReviewsService(Long storeId) {
        // 레포지토리에서 모든 리뷰찾기
        List<Review> reviewList = reviewRepository.findAll();
        // 리뷰리스트 dto에 담기위한 선언
        List<ReviewResponseDto> reviewResponseDtoList = new ArrayList<>();
        // 모든 데이터 하나씩 거치면서 확인
        for (Review review : reviewList) {
            // 훑은 데이터를 넣어줄 객체 생성
            ReviewResponseDto reviewResponseDto = new ReviewResponseDto(review);
            // 찾은 데이터를 넣은 객체를 리스트에 넣을거야
            reviewResponseDtoList.add(reviewResponseDto);
        } // 이제 그 리스트(전체 조회) 반환해
        return reviewResponseDtoList;
    }

}
