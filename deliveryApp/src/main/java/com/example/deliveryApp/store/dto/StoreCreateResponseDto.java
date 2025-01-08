package com.example.deliveryApp.store.dto;

import com.example.deliveryApp.entity.User;
import lombok.Getter;

@Getter
public class StoreCreateResponseDto {
    // 속성
    private int id;
    private User userId;
    private String storeName;
    private String openClose;
    private int deliveryMinPrice;
    // 생성자
    public StoreCreateResponseDto (int id, User userId, String storeName, String openClose, int deliveryMinPrice) {
        this.id = id;
        this.userId = userId;
        this.storeName = storeName;
        this.openClose = openClose;
        this.deliveryMinPrice = deliveryMinPrice;
    }
    // 기능

}
