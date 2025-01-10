package com.example.deliveryApp.store.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoreGetResponseDto {
    //속
    private Long id;
    private String storeName;
    private String openCloseTime;
    private int deliveryMinPrice;


}
