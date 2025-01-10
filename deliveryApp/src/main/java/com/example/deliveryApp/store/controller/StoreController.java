package com.example.deliveryApp.store.controller;

import com.example.deliveryApp.store.dto.request.StoreCreateRequestDto;
import com.example.deliveryApp.store.dto.request.StoreUpdateRequestDto;
import com.example.deliveryApp.store.dto.response.StoreCreateResponseDto;
import com.example.deliveryApp.store.dto.response.StoreGetResponseDto;
import com.example.deliveryApp.store.dto.response.StoreUpdateResponseDto;
import com.example.deliveryApp.store.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<StoreCreateResponseDto> createStoreAPI(@RequestBody StoreCreateRequestDto storeCreateRequestDto) {
        StoreCreateResponseDto storeCreated = storeService.createStoreService(storeCreateRequestDto);
        return new ResponseEntity<>(storeCreated, HttpStatus.CREATED);
    }
    // 가게 전체 조회
    @GetMapping
    public ResponseEntity<List<StoreGetResponseDto>> getAllStoreAPI() {
        List<StoreGetResponseDto> allStoresGot = storeService.getAllStoreService();
        return new ResponseEntity<>(allStoresGot, HttpStatus.OK);
    }
    // 가게 단건 조회
    @GetMapping("/{storeId}")
    public ResponseEntity<StoreGetResponseDto> getStoreAPI(@PathVariable("storeId") Long storeId) {
        StoreGetResponseDto StoreGot = storeService.getStoreService(storeId);
        return new ResponseEntity<>(StoreGot, HttpStatus.OK);
    }
    // 가게 수정
    @PatchMapping("/{storeId}")
    public ResponseEntity<StoreUpdateResponseDto> updateStoreAPI(@PathVariable("storeId") Long storeId,
                                                @RequestBody StoreUpdateRequestDto storeUpdateRequestDto) {
        StoreUpdateResponseDto storeUpdated = storeService.updateStoreService(storeId, storeUpdateRequestDto);
        return ResponseEntity.ok(storeUpdated);
    }
    // 가게 삭제
    @DeleteMapping("/{storeId}")
    public ResponseEntity<String> storeClosureAPI(@PathVariable("storeId") Long storeId) {
        storeService.storeClosureService(storeId);
        return ResponseEntity.ok("폐업처리가 완료됐습니다.");
    }
}
