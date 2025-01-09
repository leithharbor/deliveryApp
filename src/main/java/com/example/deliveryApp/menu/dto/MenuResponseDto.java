package com.example.deliveryApp.menu.dto;

import com.example.deliveryApp.entity.Menu;
import lombok.Getter;

@Getter
public class MenuResponseDto {

    private final int id;
    private final String menuName;
    private final int price;

    public MenuResponseDto(Menu menu) {
        this.id = menu.getId();
        this.menuName = menu.getMenuName();
        this.price = menu.getPrice();
    }
}