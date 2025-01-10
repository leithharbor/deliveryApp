package com.example.deliveryApp.store.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StoreCreateRequestDto {
    // 속성
    private String storeName;
    private String openCloseTime;
    private int deliveryMinPrice;

    // 생성자
//    public StoreCreateRequestDto (String storeName, String openCloseTime, int deliveryMinPrice) {
//        this.storeName = storeName;
//        this.openClose = openCloseTime;
//        this.deliveryMinPrice= deliveryMinPrice;
//    }
    // 기능

}
