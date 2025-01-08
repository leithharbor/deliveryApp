package com.example.deliveryApp.user.dto;

import com.example.deliveryApp.entity.UserType;
import lombok.Getter;

@Getter
public class SignUpRequestDto {

    private String userEmail;
    private String password;
    private String reEnterPassword;
    private String userName;
    private UserType userType;

}
