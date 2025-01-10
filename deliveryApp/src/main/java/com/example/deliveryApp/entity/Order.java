package com.example.deliveryApp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import com.example.deliveryApp.order.OrderStatus;

@Entity
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderedAt;
    private int totalPaymentPrice;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
