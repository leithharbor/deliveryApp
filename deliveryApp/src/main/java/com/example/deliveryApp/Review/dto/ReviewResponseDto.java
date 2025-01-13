package com.example.deliveryApp.Review.dto;

import com.example.deliveryApp.entity.Review;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewResponseDto {
    private final LocalDateTime createdAt;
    private final String userName;
    private final String contents;
    private final int rating;

    public ReviewResponseDto(Review review) {
        this.createdAt = review.getCreatedAt();
        this.userName = review.getUser().getUserName();
        this.contents = review.getContents();
        this.rating = review.getRating();

    }
}