package com.example.deliveryApp.Review.repository;

import com.example.deliveryApp.entity.Order;
import com.example.deliveryApp.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByOrder(Order order);

}


