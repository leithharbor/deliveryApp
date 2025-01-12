package com.example.deliveryApp.store.controller;

import com.example.deliveryApp.entity.User;
import com.example.deliveryApp.session.SessionConst;
import com.example.deliveryApp.store.dto.request.StoreCreateRequestDto;
import com.example.deliveryApp.store.dto.request.StoreUpdateRequestDto;
import com.example.deliveryApp.store.dto.response.AllStoreGetResponseDto;
import com.example.deliveryApp.store.dto.response.StoreCreateResponseDto;
import com.example.deliveryApp.store.dto.response.StoreGetResponseDto;
import com.example.deliveryApp.store.dto.response.StoreUpdateResponseDto;
import com.example.deliveryApp.store.service.StoreService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public ResponseEntity<StoreCreateResponseDto> createStoreAPI(HttpServletRequest request, @RequestBody StoreCreateRequestDto storeCreateRequestDto) {
        // 서블릿 리퀘스트로 세션값 받아오기/ 객체화
        // 사장님 아이디로 가게 생성 동일 아이디 연관짓기, 가게 주인만들기
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute(SessionConst.LOGIN_USER_ID);
        if (userId == null) {
            throw new RuntimeException("사용자 정보를 확인할 수 없습니다.");
        }
        StoreCreateResponseDto storeCreated = storeService.createStoreService(userId, storeCreateRequestDto);
        return new ResponseEntity<>(storeCreated, HttpStatus.CREATED);
    }
    // 가게 전체 조회
    @GetMapping
    public ResponseEntity<List<AllStoreGetResponseDto>> getAllStoreAPI() {
        List<AllStoreGetResponseDto> allStoresGot = storeService.getAllStoreService();
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
    public ResponseEntity<StoreUpdateResponseDto> updateStoreAPI(HttpServletRequest request, @PathVariable("storeId") Long storeId,
                                                @RequestBody StoreUpdateRequestDto storeUpdateRequestDto) {
        // 서블릿 리퀘스트로 세션값받아오기/ 객체화
        // 가게 주인만 수정 가능
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute(SessionConst.LOGIN_USER_ID);
        if (userId == null) {
            throw new RuntimeException("사용자 정보를 확인할 수 없습니다.");
        }
        StoreUpdateResponseDto storeUpdated = storeService.updateStoreService(userId, storeId, storeUpdateRequestDto);
        return ResponseEntity.ok(storeUpdated);
    }
    // 가게 삭제
    @DeleteMapping("/{storeId}")
    public ResponseEntity<String> storeClosureAPI(HttpServletRequest request, @PathVariable("storeId") Long storeId) {
        // 서블릿 리퀘스트로 세션값받아오기/ 객체화
        // 가게 주인만 가게 상태변경 가능
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute(SessionConst.LOGIN_USER_ID);
        if (userId == null) {
            throw new RuntimeException("사용자 정보를 확인할 수 없습니다.");
        }
        storeService.storeClosureService(userId, storeId);
        return ResponseEntity.ok("폐업처리가 완료됐습니다.");
    }
}
