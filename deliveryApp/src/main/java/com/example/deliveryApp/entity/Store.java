package com.example.deliveryApp.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String storeName;
    private String openClose;
    private int deliveryMinPrice;
    private boolean isDeletedStore;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



}
