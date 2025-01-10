package com.example.deliveryApp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.deliveryApp.order.OrderStatus;

@Entity
@Getter
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime orderedAt;

    @Column(nullable = false)
    private int totalPaymentPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    // public Order (Menu menu, User user, int totalPaymentPrice) {
    //     this.menu = menu;
    //     this.user = user;
    //     this.totalPaymentPrice = totalPaymentPrice;
    //     this.orderStatus = OrderStatus.PENDING; //대기 중을 기본값으로 설정
    // }

    public Order (Menu menu, int totalPaymentPrice) {
        this.menu = menu;
        this.totalPaymentPrice = totalPaymentPrice;
        this.orderStatus = OrderStatus.PENDING; //대기 중을 기본값으로 설정
    }
    
    }

}