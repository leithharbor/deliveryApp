package com.example.deliveryApp.store.dto;

import lombok.Getter;

@Getter
public class StoreCreateRequestDto {
    // 속성
    private String storeName;
    private String openClose;
    private int deliveryMinPrice;
    // 생성자
    public StoreCreateRequestDto (String storeName, String openClose, int deliveryMinPrice) {
        this.storeName = storeName;
        this.openClose = openClose;
        this.deliveryMinPrice= deliveryMinPrice;
    }
    // 기능

}
