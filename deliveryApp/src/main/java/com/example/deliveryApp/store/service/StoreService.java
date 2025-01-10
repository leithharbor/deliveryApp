package com.example.deliveryApp.store.service;

import com.example.deliveryApp.entity.Store;

import com.example.deliveryApp.store.dto.request.StoreCreateRequestDto;
import com.example.deliveryApp.store.dto.request.StoreUpdateRequestDto;
import com.example.deliveryApp.store.dto.response.StoreCreateResponseDto;
import com.example.deliveryApp.store.dto.response.StoreGetResponseDto;
import com.example.deliveryApp.store.dto.response.StoreUpdateResponseDto;
import com.example.deliveryApp.store.repository.StoreRepository;
import jakarta.transaction.Transactional;
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
        log.info("가게를 생성합니다.");
        Store store = new Store(storeCreateRequestDto.getStoreName(),
                storeCreateRequestDto.getOpenCloseTime(),
                storeCreateRequestDto.getDeliveryMinPrice());
        Store savedStore = storeRepository.save(store);
        Long storeId = savedStore.getId();
        String storeName = savedStore.getStoreName();
        String openCloseTime = savedStore.getOpenCloseTime();
        int deliveryMinPrice = savedStore.getDeliveryMinPrice();

        StoreCreateResponseDto storeCreate = new StoreCreateResponseDto(storeId, storeName, openCloseTime, deliveryMinPrice);
        return storeCreate;
    }
    // 가게 전체 조회
    public List<StoreGetResponseDto> getAllStoreService() {
        //조회 로그
        log.info("전체 가게를 조회합니다.");
        //데이터베이스에서 모두 찾기
        List<Store> storeList = storeRepository.findAll();
        //dtoList로 변환하기 위한 선언
        List<StoreGetResponseDto> storeGetResponseDtoList = new ArrayList<>();
        //storeList 데이터 전부 하나씩 거치면서 가져오기
        for (Store store : storeList) {
            StoreGetResponseDto storeGetResponseDto = new StoreGetResponseDto(
                    store.getId(), store.getStoreName(), store.getOpenCloseTime(), store.getDeliveryMinPrice());
            storeGetResponseDtoList.add(storeGetResponseDto);
        } return storeGetResponseDtoList;
    }
    // 가게 단건 조회
    public StoreGetResponseDto getStoreService(Long storeId) {
        log.info("입력한 가게를 조회합니다.");
        // 1. 데이터베이스에 storeId로 해당 가게 조회/ 없으면 찾을 수 없다고 안내 메시지(예외처리)
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));
        // 2. 가게 데이터 불러오기
        Long id = store.getId();
        String storeName = store.getStoreName();
        String openCloseTime = store.getOpenCloseTime();
        int deliveryMinPrice = store.getDeliveryMinPrice();
        StoreGetResponseDto storeGetResponseDto = new StoreGetResponseDto(id, storeName, openCloseTime, deliveryMinPrice);
        return storeGetResponseDto;
    }
    // 가게 수정
    // @Transaction 어노테이션으로 영속성 컨텍스트 사용./save(); 안써도 자동으로 수정사항 저장시켜주는 애.
    @Transactional
    public StoreUpdateResponseDto updateStoreService(Long storeId, StoreUpdateRequestDto storeUpdateRequestDto) {
        log.info("가게 정보를 수정합니다.");
        // 1. 수정할 가게 찾기/ 없으면 찾을 수 없다고 안내 메시지(예외처리)
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));
        log.info("수정할 가게 찾기");
        // 수정할 내용 store 엔티티에 생성자(조립설명서)의 조건하에 수정 가능
        store.storeUpdateInfo(
                storeUpdateRequestDto.getStoreName(),
                storeUpdateRequestDto.getOpenCloseTime(),
                storeUpdateRequestDto.getDeliveryMinPrice());
        log.info("수정2222");
        // 수정 내용 StoreUpdateResponseDto에 담아서 반환하는 작업
        StoreUpdateResponseDto storeUpdateResponseDto = new StoreUpdateResponseDto(
                storeId, store.getStoreName(), store.getOpenCloseTime(), store.getDeliveryMinPrice());
        log.info("333333");
        return storeUpdateResponseDto;
    }
    // 가게 삭제
    public void storeClosureService(Long storeId) {
        log.info("가게 정보를 삭제합니다.");
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));
        log.info("삭제할 가게 찾기");
        store.setDeletedStore(true);
        log.info("가게 상태 변경");
        storeRepository.save(store);
    }
}
