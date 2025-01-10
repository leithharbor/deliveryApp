package com.example.deliveryApp.store.controller;

import com.example.deliveryApp.store.dto.request.StoreCreateRequestDto;
import com.example.deliveryApp.store.dto.request.StoreUpdateRequestDto;
import com.example.deliveryApp.store.dto.response.StoreCreateResponseDto;
import com.example.deliveryApp.store.dto.response.StoreGetResponseDto;
import com.example.deliveryApp.store.dto.response.StoreUpdateResponseDto;
import com.example.deliveryApp.store.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    //가게 생성
    @PostMapping
    public StoreCreateResponseDto createStoreAPI(@RequestBody StoreCreateRequestDto storeCreateRequestDto) {
        StoreCreateResponseDto createStore = storeService.createStoreService(storeCreateRequestDto);
        return createStore;
    }
    // 가게 전체 조회
    @GetMapping
    public List<StoreGetResponseDto> getAllStoreAPI() {
        List<StoreGetResponseDto> allStoresFound = storeService.getAllStoreService();
        return allStoresFound;
    }
    // 가게 단건 조회
    @GetMapping("/{storeId}")
    public StoreGetResponseDto getStoreAPI(@PathVariable("storeId") Long storeId) {
        StoreGetResponseDto storeFound = storeService.getStoreService(storeId);
        return storeFound;
    }
    // 가게 수정
    @PatchMapping("/{storeId}")
    public StoreUpdateResponseDto updateStoreAPI(@PathVariable("storeId") Long storeId,
                                                @RequestBody StoreUpdateRequestDto storeUpdateRequestDto) {
        StoreUpdateResponseDto storeUpdateResponseDto = storeService.updateStoreService(storeId, storeUpdateRequestDto);
        return storeUpdateResponseDto;
    }
    // 가게 삭제
    @DeleteMapping("/{storeId")
    public void storeClosureAPI(@PathVariable("storeId") Long studentId) {
        storeService.
    }
}
