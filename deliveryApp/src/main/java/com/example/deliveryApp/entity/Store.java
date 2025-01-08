package com.example.deliveryApp.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String storeName;
    private String openClose;
    private int deliveryMinPrice;
    private LocalDateTime createdAt;
    private boolean isDeletedStore = true;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Store () {

    }

}
