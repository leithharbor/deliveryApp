package com.example.deliveryApp.entity;


import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String userEmail;
    private String password;
    private boolean userType;
    private boolean isDelete;

    public User () {

    }

}
