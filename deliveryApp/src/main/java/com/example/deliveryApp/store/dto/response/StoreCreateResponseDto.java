package com.example.deliveryApp.store.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StoreCreateResponseDto {
    // 속성
    private String createMessage;
    private Long id;
    private String storeName;
    private String openCloseTime;
    private int deliveryMinPrice;

    // 생성자
//    public StoreCreateResponseDto (Long id, String storeName, String openCloseTime, int deliveryMinPrice) {
//        this.id = id;
//        this.storeName = storeName;
//        this.openClose = openCloseTime;
//        this.deliveryMinPrice = deliveryMinPrice;
//    }
    // 기능

}
