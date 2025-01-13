package com.example.deliveryApp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "menu")
public class Menu {

    @Id @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String menuName;
    private int price;

    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    //생성자
    public Menu(String menuName, int price, Store store) {
        this.menuName = menuName;
        this.price = price;
        this.store = store;
        this.isActive = true; // 기본적으로 활성 상태로 설정 -> 삭제 시, 메뉴의 상태만 삭제 상태로 변경
    }

    public Menu() {
    }

    public void updateMenu(String name, int price) {
        this.menuName = menuName;
        this.price = price;
    }

    public void deleteMenu() {
        this.isActive = false; //삭제시 상태 변경
    }
}





