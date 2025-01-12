package com.example.deliveryApp.store.dto.response;

import com.example.deliveryApp.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoreGetResponseDto {
    /**
     * 단건 가게 조회 Response DTO
     */
    //속
    private Long id;
    private String storeName;
    private String openCloseTime;
    private int deliveryMinPrice;
    private long menuId;
    // 생성자
//    public StoreGetResponseDto(Long id, String storeName, String openCloseTime, int deliveryMinPrice) {
//        this.id = id;
//        this.storeName = storeName;
//        this.openCloseTime = openCloseTime;
//        this.deliveryMinPrice = deliveryMinPrice;
//    }

    public StoreGetResponseDto(Store store) {
        this.id = store.getId();
        this.storeName = store.getStoreName();
        this.openCloseTime = store.getOpenCloseTime();
        this.deliveryMinPrice = store.getDeliveryMinPrice();
    }





}
