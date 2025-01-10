package com.example.deliveryApp.store.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoreUpdateRequestDto {
    private String storeName;
    private String openCloseTime;
    private int deliveryMinPrice;
}
