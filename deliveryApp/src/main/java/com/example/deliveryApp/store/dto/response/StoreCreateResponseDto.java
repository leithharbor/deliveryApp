package com.example.deliveryApp.store.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StoreCreateResponseDto {
    // 속성
    private Long id;
    private String storeName;
    private String openClose;
    private int deliveryMinPrice;
    // 생성자
//    public StoreCreateResponseDto (Long id, String storeName, String openClose, int deliveryMinPrice) {
//        this.id = id;
//        this.storeName = storeName;
//        this.openClose = openClose;
//        this.deliveryMinPrice = deliveryMinPrice;
//    }
    // 기능

}
