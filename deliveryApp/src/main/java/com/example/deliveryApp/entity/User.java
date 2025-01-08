package com.example.deliveryApp.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userName;
    private String userEmail;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String userType;
    private boolean isDelete;

    public User () {

    }

}
