package com.example.deliveryApp.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length = 10) // 이름은 10자까지 적을 수 있다
    private String userName;

    @Column(length = 50, unique = true) // 이메일은 50자까지 적을 수 있다
    private String userEmail;

    @Column(length = 100) // 비밀번호는 25자까지 적을 수 있다
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Setter
    @Column(columnDefinition = "TINYINT")
    private boolean isDelete = false; // 회원 탈퇴 여부는 항상 false

    public User(String userEmail, String password, String userName, UserType userType) {
        this.userEmail = userEmail;
        this.password = password;
        this.userName = userName;
        this.userType = userType;
    }

}
