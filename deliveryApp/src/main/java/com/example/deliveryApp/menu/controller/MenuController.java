package com.example.deliveryApp.menu.controller;

import com.example.deliveryApp.entity.User;
import com.example.deliveryApp.menu.dto.MenuRequestDto;
import com.example.deliveryApp.menu.service.MenuService;
import com.example.deliveryApp.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    // 메뉴 생성
    @PostMapping
    public ResponseEntity<String> createMenu(@RequestBody MenuRequestDto menuRequestDto,
                                             HttpServletRequest request) {

        // 세션에서 유저 확인
        Long userId = (Long)request.getSession().getAttribute(SessionConst.LOGIN_USER_ID);

        // 로그인이 null이면
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인하세요.");
        }

        menuService.createMenu(userId, menuRequestDto);
        return ResponseEntity.ok("메뉴가 생성되었습니다.");
    }


    // 메뉴 수정
    @PatchMapping("/{menuId}")
    public ResponseEntity<String> updateMenu(
            @PathVariable("menuId") Long menuId,
            @RequestBody MenuRequestDto menuRequestDto,
            HttpServletRequest request) {

        Long userId = (Long) request.getSession().getAttribute(SessionConst.LOGIN_USER_ID);

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        menuService.updateMenu(userId, menuId, menuRequestDto);
        return ResponseEntity.ok("메뉴가 수정되었습니다.");
    }


    // 메뉴 삭제
    @DeleteMapping("/{menuId}")
    public ResponseEntity<String> deleteMenu(@PathVariable("menuId") Long menuId, HttpServletRequest request) {

        Long userId = (Long) request.getSession().getAttribute(SessionConst.LOGIN_USER_ID);

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        menuService.deleteMenu(userId, menuId);
        return ResponseEntity.ok("메뉴가 삭제되었습니다.");
    }
}




