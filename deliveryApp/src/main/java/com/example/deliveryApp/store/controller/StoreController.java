package com.example.deliveryApp.store.controller;

import com.example.deliveryApp.store.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/stores")
public class StoreController {
    // 속성
    private final StoreService storeService;
    // 생성자
    public StoreController (StoreService storeService) {
        this.storeService = storeService;
    }
    // 기능


}
