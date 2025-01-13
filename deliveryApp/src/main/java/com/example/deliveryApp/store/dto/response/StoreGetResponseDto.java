package com.example.deliveryApp.store.dto.response;

import com.example.deliveryApp.entity.Menu;
import com.example.deliveryApp.entity.Store;
import com.example.deliveryApp.menu.dto.MenuResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

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
    private List<MenuResponseDto> menuList;

    public StoreGetResponseDto(Store store, List<MenuResponseDto> menuList) {
        this.id = store.getId();
        this.storeName = store.getStoreName();
        this.openCloseTime = store.getOpenCloseTime();
        this.deliveryMinPrice = store.getDeliveryMinPrice();
        this.menuList = menuList;
    }





}
