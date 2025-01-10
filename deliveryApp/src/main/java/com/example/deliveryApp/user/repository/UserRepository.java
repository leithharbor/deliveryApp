package com.example.deliveryApp.user.repository;

import com.example.deliveryApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUserEmail(String userEmail);

    Boolean existsByUserEmail(String userEmail);
}
