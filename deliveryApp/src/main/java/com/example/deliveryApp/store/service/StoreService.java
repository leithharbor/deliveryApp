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
        //storeList 한번씩 거쳐오기
        for (Store store : storeList) {
            StoreGetResponseDto storeGetResponseDto = new StoreGetResponseDto(store.getId(), store.getStoreName(), store.getOpenClose(), store.getDeliveryMinPrice());
            storeGetResponseDtoList.add(storeGetResponseDto);
        } return storeGetResponseDtoList;
    }


}
