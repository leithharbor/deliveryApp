package com.example.deliveryApp.menu.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenuRequestDto {


    private String menuName;
    private int price;
    private int storeId;

    public MenuRequestDto(String menuName, int price, int storeId) {
        this.menuName = menuName;
        this.price = price;
        this.storeId = storeId;
    }
}