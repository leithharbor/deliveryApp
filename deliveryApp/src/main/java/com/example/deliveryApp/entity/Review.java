package com.example.deliveryApp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "review")
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rating;
    private LocalDateTime createdAt;
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    public Review(Order order, Store store, User user, int rating, String contents) {
        this.order = order;
        this.store = store;
        this.user = user;
        this.rating = rating;
        this.contents = contents;
        this.createdAt = LocalDateTime.now();
    }
}
