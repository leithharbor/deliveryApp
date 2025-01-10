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
    private String openCloseTime;
//    private String storeDescription;
    private int deliveryMinPrice;
    private boolean isDeletedStore;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 엔티티에 무조건적으로 필요한 기본생성자
    protected Store () {
    }

    public Store(String storeName, String openCloseTime, int deliveryMinPrice){
        this.storeName = storeName;
        this.openCloseTime = openCloseTime;
        this.deliveryMinPrice = deliveryMinPrice;
        this.isDeletedStore = false;
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
