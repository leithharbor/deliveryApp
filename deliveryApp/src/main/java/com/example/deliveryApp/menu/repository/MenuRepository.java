package com.example.deliveryApp.menu.repository;

import com.example.deliveryApp.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    // Id로 메뉴 조회 -> 없으면 예외 발생
    default Menu findByIdOrElseThrow(long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "메뉴가 없습니다."));
    }
}
