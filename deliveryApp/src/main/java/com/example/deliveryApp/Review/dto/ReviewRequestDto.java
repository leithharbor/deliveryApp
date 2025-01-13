package com.example.deliveryApp.Review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class ReviewRequestDto {
    @Min(1)
    @Max(5)
    private int rating;

    private String contents;
    private Long orderId;
}