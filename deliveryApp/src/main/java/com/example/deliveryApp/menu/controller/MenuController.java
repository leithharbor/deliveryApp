package com.example.deliveryApp.menu.controller;

import com.example.deliveryApp.entity.User;
import com.example.deliveryApp.menu.dto.MenuRequestDto;
import com.example.deliveryApp.menu.service.MenuService;
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
    public ResponseEntity<String> createMenu(@RequestBody MenuRequestDto menuRequestDto) {

        menuService.createMenu(menuRequestDto);
        return new ResponseEntity<>("메뉴가 생성되었습니다.", HttpStatus.CREATED);
    }

    // 메뉴 수정
    @PatchMapping("/{menuId}")
    public ResponseEntity<String> updateMenu(
            @PathVariable("menuId") Long menuId,
            @RequestBody MenuRequestDto menuRequestDto) {

        menuService.updateMenu(menuId, menuRequestDto);
        return new ResponseEntity<>("메뉴가 수정되었습니다.", HttpStatus.OK);
    }

    // 메뉴 삭제
    @DeleteMapping("/{menuId}")
    public ResponseEntity<String> deleteMenu(@PathVariable("menuId") Long menuId) {

        menuService.deleteMenu(menuId);
        return new ResponseEntity<>("메뉴가 삭제되었습니다.", HttpStatus.OK);
    }
}





