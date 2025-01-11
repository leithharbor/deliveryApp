package com.example.deliveryApp.Review.controller;

import com.example.deliveryApp.Review.dto.ReviewRequestDto;
import com.example.deliveryApp.Review.dto.ReviewResponseDto;
import com.example.deliveryApp.Review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 생성
    @PostMapping
    public ResponseEntity<ReviewResponseDto> createReview(@RequestBody ReviewRequestDto reviewRequestDto) {
        ReviewResponseDto reviewResponseDto = reviewService.createReview(reviewRequestDto);
        return ResponseEntity.ok(reviewService.createReview(reviewRequestDto));
    }
}

