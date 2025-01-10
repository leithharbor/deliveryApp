package com.example.deliveryApp.store.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoreUpdateResponseDto {
    private Long id;
    private String storeName;
    private String openCloseTime;
    private int deliveryMinTime;
}
