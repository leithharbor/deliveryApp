package com.example.deliveryApp.store.service;

import com.example.deliveryApp.store.repository.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StoreService {
    // 속성
    private final StoreRepository storeRepository;
    // 생성자
    public StoreService (StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }
    // 기능

}
