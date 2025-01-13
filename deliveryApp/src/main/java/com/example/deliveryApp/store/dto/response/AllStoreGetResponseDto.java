package com.example.deliveryApp.store.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AllStoreGetResponseDto {
    /**
     * 전체 가게 조회 Response DTO
     */
    private Long id;
    private String storeName;
    private String openCloseTime;
    private int deliveryMinPrice;

}
