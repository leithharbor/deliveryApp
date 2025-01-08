package com.example.deliveryApp.user.dto;

import lombok.Getter;

@Getter
public class SignUpRequestDto {

    private String userEmail;
    private String password;
    private String reEnterPassword;
    private String userName;
    private String userType;

}
