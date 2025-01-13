package com.example.deliveryApp.store.dto.response;

import com.example.deliveryApp.entity.Store;
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
    public StoreCreateResponseDto(String createMessage, Store savedStore) {
        this.createMessage = createMessage;
        this.id = savedStore.getId();
        this.storeName = savedStore.getStoreName();
        this.openCloseTime = savedStore.getOpenCloseTime();
        this.deliveryMinPrice = savedStore.getDeliveryMinPrice();
    }
    // 기능

}
