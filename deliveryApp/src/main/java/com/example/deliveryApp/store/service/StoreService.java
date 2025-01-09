package com.example.deliveryApp.store.service;

import com.example.deliveryApp.entity.Store;

import com.example.deliveryApp.store.dto.request.StoreCreateRequestDto;
import com.example.deliveryApp.store.dto.response.StoreCreateResponseDto;
import com.example.deliveryApp.store.dto.response.StoreGetResponseDto;
import com.example.deliveryApp.store.repository.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class StoreService {
    // 속성
    private final StoreRepository storeRepository;
    // 생성자
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }
    // 기능
    // 가게 생성
    public StoreCreateResponseDto createStoreService(StoreCreateRequestDto storeCreateRequestDto) {
        log.info("가게 생성합니다.");
        Store store = new Store(storeCreateRequestDto.getStoreName(), storeCreateRequestDto.getOpenClose(), storeCreateRequestDto.getDeliveryMinPrice());
        Store savedStore = storeRepository.save(store);
        Long storeId = savedStore.getId();
        String storeName = savedStore.getStoreName();
        String openClose = savedStore.getOpenClose();
        int deliveryMinPrice = savedStore.getDeliveryMinPrice();

        StoreCreateResponseDto storeCreate = new StoreCreateResponseDto(storeId, storeName, openClose, deliveryMinPrice);
        return storeCreate;
    }
    // 가게 전체 조회
    public List<StoreGetResponseDto> getAllStoreService() {
        //조회 로그
        log.info("전체 가게 조회합니다.");
        //데이터베이스에서 모두 찾기
        List<Store> storeList = storeRepository.findAll();
        //dtoList로 변환하기 위한 선언
        List<StoreGetResponseDto> storeGetResponseDtoList = new ArrayList<>();
        //storeList 데이터 전부 하나씩 거치면서 가져오기
        for (Store store : storeList) {
            StoreGetResponseDto storeGetResponseDto = new StoreGetResponseDto(store.getId(), store.getStoreName(), store.getOpenClose(), store.getDeliveryMinPrice());
            storeGetResponseDtoList.add(storeGetResponseDto);
        } return storeGetResponseDtoList;
    }
    // 가게 단건 조회
    public StoreGetResponseDto getStoreService(Long storeId) {
        // 1. 데이터베이스에 storeId로 해당 가게 조회
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));
        // 2. 가게 데이터 불러오기
        Long id = store.getId();
        String storeName = store.getStoreName();
        String openClose = store.getOpenClose();
        int deliveryMinPrice = store.getDeliveryMinPrice();
        StoreGetResponseDto storeGetResponseDto = new StoreGetResponseDto(id, storeName, openClose, deliveryMinPrice);
        return storeGetResponseDto;
    }

}
