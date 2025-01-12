package com.example.deliveryApp.store.service;

import com.example.deliveryApp.entity.*;

import com.example.deliveryApp.entity.Menu;
import com.example.deliveryApp.menu.dto.MenuResponseDto;
import com.example.deliveryApp.menu.repository.MenuRepository;
import com.example.deliveryApp.store.dto.request.StoreCreateRequestDto;
import com.example.deliveryApp.store.dto.request.StoreUpdateRequestDto;
import com.example.deliveryApp.store.dto.response.AllStoreGetResponseDto;
import com.example.deliveryApp.store.dto.response.StoreCreateResponseDto;
import com.example.deliveryApp.store.dto.response.StoreGetResponseDto;
import com.example.deliveryApp.store.dto.response.StoreUpdateResponseDto;
import com.example.deliveryApp.store.repository.StoreRepository;
import com.example.deliveryApp.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.awt.SystemColor.menu;


@Slf4j
@RequiredArgsConstructor
@Service
public class StoreService {
    // 속성
    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final UserRepository userRepository;

    // 기능
    // 가게 생성
    // 사장님 권한을 가진 유저만 가게를 최대 3개까지 생성할 수 있다. (예외처리)
    public StoreCreateResponseDto createStoreService(Long userId, StoreCreateRequestDto storeCreateRequestDto) {
        /*
         * 가게 생성전에 사장님 권한을 가진 유저인지 검증
         * 1. 사장님 권한이 아닌 유저면 가게를 생성할 수 없음(예외처리)
         * 2. 한 사장님당 가게 3개까지만 만들 수 있음. 유저아이디로 가게 몇개 만들었는지 확인하고 4개째 시도할 때 거절
         * 먼저 유저 아이디부터 찾아오기
          */
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        // 찾은 유저아이디에서 유저타입이 오너가 아니라면 예외처리해버리기
        // 아래 if문 작성하는데만 2시간정도 걸린듯. 결국 쓰는 방법 찾아냄.
        if (!user.getUserType().equals(UserType.OWNER)) {
            throw new RuntimeException("사장님만 창업할 수 있습니다.");
        }
        // 가게 3개 넘어가는지 확인. 넘어가면 쳐내기 (이건 솔직히 챗지피티 보고만듦...)(그래도 썼다 지웠다 반복했음...)
        int countStore = storeRepository.countByUser(user);
        // 처음에 countStore > 3 라고 했는데 4개까지 만들어지고 5개째에서 예외처리발생됐다. 수식을 이해못하겠다..
        if (countStore >= 3) {
            throw new RuntimeException("가게는 3개까지만 생성할 수 있습니다.");
        }
        // 스토어 객체 만들기 + 가게 창업한 사장님 유저정보 같이 저장
        Store store = new Store(storeCreateRequestDto.getStoreName(),
                storeCreateRequestDto.getOpenCloseTime(),
                storeCreateRequestDto.getDeliveryMinPrice());
        // request 값 저장
        Store savedStore = storeRepository.save(store);
        StoreCreateResponseDto storeCreate = new StoreCreateResponseDto("가게가 생성되었습니다.", savedStore);
        return storeCreate;
    }
    // 가게 전체 조회
    // 가게 조회시 폐업상태인 가게는 조회안되게 하기
    public List<AllStoreGetResponseDto> getAllStoreService(Long storeId) {
//        Store findStore = storeRepository.findById(storeId)
//                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));
//        boolean deletedStore = findStore.isDeletedStore();
//        if (!deletedStore == false) {
//
//        }
        //조회 로그
        log.info("전체 가게를 조회합니다.");
        //데이터베이스(레포지토리)에서 StoreStatus OPENED인 상태인 애들 조회하기
        List<Store> storeList = storeRepository.findAllByStoreStatus(StoreStatus.OPENED);
        //dto List로 변환하기 위한 선언
        List<AllStoreGetResponseDto> allStoreGetResponseDtoList = new ArrayList<>();
        //storeList 데이터 전부 하나씩 거치면서 가져오기
        for (Store store : storeList) {
            AllStoreGetResponseDto allStoreGetResponseDto = new AllStoreGetResponseDto(
                    store.getId(), store.getStoreName(), store.getOpenCloseTime(), store.getDeliveryMinPrice());
            allStoreGetResponseDtoList.add(allStoreGetResponseDto);
        } return allStoreGetResponseDtoList;
    }
    // 가게 단건 조회
    public StoreGetResponseDto getStoreService(Long storeId) {
        log.info("입력한 가게를 조회합니다.");
        // 1. 데이터베이스에 storeId로 해당 가게 조회/ 없으면 찾을 수 없다고 안내 메시지(예외처리)
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));
        log.info("Received ID: {}", storeId);
        List<Menu> menuList = menuRepository.findAllByStoreId(storeId);

        List<MenuResponseDto> menuResponseDtoList = menuList.stream().map(menu -> new MenuResponseDto(
                        menu.getId(),
                        menu.getMenuName(),
                        menu.getPrice()))
                .collect(Collectors.toList());

//        if (!menu.getStore().getId().equals(storeId)) {
//            throw new RuntimeException("잘못 매칭된 메뉴");
//        }
//        List<Menu> menuList = menuRepository.findAll();
//        List<MenuResponseDto> menuResponseDtoList = new ArrayList<>();
//        for (Menu menu1 : menuList) {
//            MenuResponseDto menuResponseDto = new MenuResponseDto(menu);
//            menuResponseDtoList.add(menuResponseDto);
//        }
//        log.info("Received ID: {}", menu.getId());
        // 2. 가게 데이터 불러오기
        StoreGetResponseDto storeGetResponseDto = new StoreGetResponseDto(store, menuResponseDtoList);
        return storeGetResponseDto;
    }
    // 가게 수정
    // @Transaction 어노테이션으로 영속성 컨텍스트 사용./save(); 안써도 자동으로 수정사항 저장시켜주는 애.
    @Transactional
    public StoreUpdateResponseDto updateStoreService(Long userId, Long storeId, StoreUpdateRequestDto storeUpdateRequestDto) {
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
        StoreUpdateResponseDto storeUpdateResponseDto = new StoreUpdateResponseDto("가게 정보를 수정했습니다.",
                storeId, store.getStoreName(), store.getOpenCloseTime(), store.getDeliveryMinPrice());
        log.info("333333");
        return storeUpdateResponseDto;
    }
    // 가게 운영 상태 운영중(개업)으로 변경
    public void startStoreService(Long userId, Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));
        store.setStoreStatus(StoreStatus.OPENED);
        storeRepository.save(store);
//        StoreStatus opened = store.getStoreStatus().OPENED;
    }

    // 가게 폐업
    public void storeClosureService(Long userId, Long storeId) {
        log.info("가게 상태를 변경합니다.");
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));
        log.info("상태 변경할 가게 찾기");
        store.setStoreStatus(StoreStatus.SHUTDOWN);
        log.info("가게 운영중으로 상태 변경");
        storeRepository.save(store);
    }
}
