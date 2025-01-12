package com.example.deliveryApp.entity;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;

@Getter
@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Long 타입으로 변경
    private String storeName;
    private String openCloseTime;
    private int deliveryMinPrice;
    @Setter
    // false == 폐업 (폐업과 창업은 동일한 상태로 봐야함. 실질적으로는 운영안함과 운영중으로 나뉨)
    // true == 운영중
    private boolean isDeletedStore = false; // false(폐업)을 기본값으로 설정.

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 엔티티에 무조건적으로 필요한 기본생성자
    protected Store () {
    }
    // 스토어 객체 생성자(사장님 유저와 생성가게 연관짓기)
    public Store(String storeName, String openCloseTime, int deliveryMinPrice, User user){
        this.storeName = storeName;
        this.openCloseTime = openCloseTime;
        this.deliveryMinPrice = deliveryMinPrice;
        this.user = user;
    }
    // 수정 기능 - 수정할때 지켜야할 최소 조건 정해주기(어플 정책 설정)
    public void storeUpdateInfo (String storeName, String openCloseTime, int deliveryMinPrice) {
        if (storeName != null && !storeName.isEmpty()) {
            this.storeName = storeName;
        }
        if (openCloseTime != null && !openCloseTime.isEmpty()) {
            this.openCloseTime = openCloseTime;
        }
        if (deliveryMinPrice > 0) {
            this.deliveryMinPrice = deliveryMinPrice;
        }
    }


}
