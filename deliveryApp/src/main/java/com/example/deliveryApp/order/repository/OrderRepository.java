package com.example.deliveryApp.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.deliveryApp.entity.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    //로그인 한 유저가 주문한 내역을 List형태로 조회
    List<Order> findAllByUser_UserId(Long userUserId);
}
