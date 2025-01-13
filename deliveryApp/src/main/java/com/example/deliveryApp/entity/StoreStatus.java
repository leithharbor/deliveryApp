package com.example.deliveryApp.entity;

// 가게 상태를 결정하는 enum. 가게 상태는 개업전(창업), 운영중, 폐업 으로 나뉜다.
public enum StoreStatus {
    NOTOPENED, // 개업전(창업)
    OPENED, // 운영중(개업)
    SHUTDOWN // 폐업
}
