package com.example.deliveryApp.user.controller;

import com.example.deliveryApp.user.dto.SignUpRequestDto;
import com.example.deliveryApp.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //회원 가입 API
    @PostMapping("/signup")
    public ResponseEntity<String> userSignUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        userService.userSignUp(signUpRequestDto);
        return new ResponseEntity<>("회원 가입이 완료 되었습니다.", HttpStatus.CREATED);
    }

    //회원 탈퇴 API
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> disableAccount(
            @PathVariable(name = "user_id") Long userId,
            @RequestParam("password") String password) {

    }
}
