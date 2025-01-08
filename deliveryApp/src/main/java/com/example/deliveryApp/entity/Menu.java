package com.example.deliveryApp.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String menuName;
    private int price;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}
