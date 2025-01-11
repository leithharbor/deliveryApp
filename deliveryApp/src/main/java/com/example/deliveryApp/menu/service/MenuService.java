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
import com.example.deliveryApp.entity.User;
import com.example.deliveryApp.entity.UserType;
import com.example.deliveryApp.menu.dto.MenuRequestDto;
import com.example.deliveryApp.menu.dto.MenuResponseDto;
import com.example.deliveryApp.menu.repository.MenuRepository;
import com.example.deliveryApp.store.repository.StoreRepository;
import com.example.deliveryApp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;

    private final UserRepository userRepository;

    // 사장님 권한
    private void checkOwner(Long userId) {
        if (userId == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("아이디가 없습니다."));

        if (user.getUserType() != UserType.OWNER) {
            throw new IllegalStateException("사장님만 접근 가능합니다.");
        }
    }


    // 메뉴 생성
    public MenuResponseDto createMenu(Long userId, MenuRequestDto menuRequestDto) {

        // 사장님 권한
        checkOwner(userId);

        //가게 조회
        Store store = storeRepository.findById(menuRequestDto.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("해당 가게를 찾을 수 없습니다."));

        Menu menu = new Menu(menuRequestDto.getMenuName(), menuRequestDto.getPrice(), store);

        // 메뉴 저장
        menuRepository.save(menu);
        return new MenuResponseDto(menu);
    }

    // 메뉴 수정
    @Transactional
    public MenuResponseDto updateMenu(Long userId, Long menuId, MenuRequestDto menuRequestDto) {

        // 사장님 권한
        checkOwner(userId);

        // 수정할 메뉴 조회 -> 없으면 예외 발생
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("해당 메뉴를 찾을 수 없습니다."));

        // 메뉴 이름과 가격 수정
        menu.updateMenu(menuRequestDto.getMenuName(), menuRequestDto.getPrice());
        return new MenuResponseDto(menu);
    }

    // 메뉴 삭제
    @Transactional
    public void deleteMenu(Long userId, Long menuId) {

        // 사장님 권한
        checkOwner(userId);

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("해당 메뉴를 찾을 수 없습니다."));

        // 메뉴 삭제 상태로 변경 (isActive -> false)
        menu.deleteMenu();
    }

}

