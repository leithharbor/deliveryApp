package com.example.deliveryApp.entity;


import jakarta.persistence.*;
import lombok.Getter;



@Getter
@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Long 타입으로 변경
    private String storeName;
    private String openClose;
    private int deliveryMinPrice;
    private boolean isDeletedStore;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    protected Store () {
    }
    public Store (String storeName, String openClose, int deliveryMinPrice){
        this.storeName = storeName;
        this.openClose = openClose;
        this.deliveryMinPrice = deliveryMinPrice;
        this.isDeletedStore = false;
    }



}
