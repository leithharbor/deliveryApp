package com.example.deliveryApp.user.repository;

import com.example.deliveryApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Long, User> {
}
