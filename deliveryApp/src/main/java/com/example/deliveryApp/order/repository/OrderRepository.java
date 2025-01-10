package com.example.deliveryApp.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.deliveryApp.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
