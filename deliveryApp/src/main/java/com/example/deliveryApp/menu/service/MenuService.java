//package com.example.deliveryApp.menu.service;
//
//import com.example.deliveryApp.entity.Menu;
//import com.example.deliveryApp.entity.Store;
//import com.example.deliveryApp.menu.dto.MenuRequestDto;
//import com.example.deliveryApp.menu.dto.MenuResponseDto;
//import com.example.deliveryApp.menu.repository.MenuRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import jakarta.transaction.Transactional;
//
//
//
//@Service
//@RequiredArgsConstructor
//public class MenuService {
//
//    private final MenuRepository menuRepository;
//    private final StoreRepository storeRepository;
//
//
//    // 메뉴 생성
//    public MenuResponseDto createMenu(MenuRequestDto menuRequestDto) {
//        // 가게 조회 -> 없으면 예외 발생
//        Store store = storeRepository.findByIdOrElseThrow(menuRequestDto.getStoreId());
//
//        // 메뉴 생성
//        Menu menu = new Menu(menuRequestDto.getMenuName(), menuRequestDto.getPrice(), store);
//
//        // 메뉴 저장
//        menuRepository.save(menu);
//        return new MenuResponseDto(menu);
//    }
//
//
//    // 메뉴 수정
//    @Transactional
//    public MenuResponseDto updateMenu(Long menuId, MenuRequestDto menuRequestDto) {
//        //Store store = storeRepository.findByIdOrElseThrow(menuRequestDto.getStoreId());
//
//        // 수정할 메뉴 조회 -> 없으면 예외 발생
//        Menu menu = menuRepository.findByIdOrElseThrow(menuId);
//
//        // 메뉴 이름과 가격 수정
//        menu.updateMenu(menuRequestDto.getMenuName(), menuRequestDto.getPrice());
//        return new MenuResponseDto(menu);
//
//    }
//
//
//    // 메뉴 삭제
//    @Transactional
//    public void deleteMenu(Long menuId) {
//        //Store store = storeRepository.findByIdOrElseThrow(menuRequestDto.getStoreId());
//        Menu menu = menuRepository.findByIdOrElseThrow(menuId);
//
//        // 메뉴 삭제 상태로 변경 (isActive -> false)
//        menu.deleteMenu();
//    }
//
//}
//
//
//

package com.example.deliveryApp.menu.service;

import com.example.deliveryApp.entity.Menu;
import com.example.deliveryApp.entity.Store;
import com.example.deliveryApp.menu.dto.MenuRequestDto;
import com.example.deliveryApp.menu.dto.MenuResponseDto;
import com.example.deliveryApp.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    //private final StoreRepository storeRepository;

    // 메뉴 생성
    public MenuResponseDto createMenu(MenuRequestDto menuRequestDto) {
        //가게 조회
        //Store store = storeRepository.findByIdOrElseThrow(menuRequestDto.getStoreId());
        // 아직 없어서 Store를 null로 처리
        Menu menu = new Menu(menuRequestDto.getMenuName(), menuRequestDto.getPrice(), null);

        // 메뉴 저장
        menuRepository.save(menu);
        return new MenuResponseDto(menu);
    }

    // 메뉴 수정
    @Transactional
    public MenuResponseDto updateMenu(Long menuId, MenuRequestDto menuRequestDto) {
        // 수정할 메뉴 조회 -> 없으면 예외 발생
        Menu menu = menuRepository.findByIdOrElseThrow(menuId);

        // 메뉴 이름과 가격 수정
        menu.updateMenu(menuRequestDto.getMenuName(), menuRequestDto.getPrice());
        return new MenuResponseDto(menu);
    }

    // 메뉴 삭제
    @Transactional
    public void deleteMenu(Long menuId) {
        Menu menu = menuRepository.findByIdOrElseThrow(menuId);

        // 메뉴 삭제 상태로 변경 (isActive -> false)
        menu.deleteMenu();
    }
}

