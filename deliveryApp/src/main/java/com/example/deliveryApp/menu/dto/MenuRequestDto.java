package com.example.deliveryApp.menu.dto;

import lombok.Getter;

@Getter
public class MenuRequestDto {


    private final String menuName;
    private final int price;
    private final int storeId;

    public MenuRequestDto(String menuName, int price, int storeId) {
        this.menuName = menuName;
        this.price = price;
        this.storeId = storeId;
    }
}