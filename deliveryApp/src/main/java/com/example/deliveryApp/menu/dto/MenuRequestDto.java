package com.example.deliveryApp.menu.dto;

public class MenuRequestDto {


    private final String menuName;
    private final int price;

    public MenuRequestDto(String menuName, int price) {
        this.menuName = menuName;
        this.price = price;
    }
}