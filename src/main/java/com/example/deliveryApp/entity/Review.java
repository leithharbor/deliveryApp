package com.example.deliveryApp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "review")
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int rating;
    private LocalDateTime createdAt;
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}
