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
        userService.userSignUp(signUpRequestDto); // 서비스의 회원가입 기능을 호출
        return ResponseEntity.status(HttpStatus.CREATED).body("회원 가입이 완료 되었습니다.");
    }

    //회원 탈퇴 API
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> disableAccount(
            @PathVariable(name = "userId") Long userId, // PathVariable로 유저 Id 받기
            @RequestParam("password") String password) {

        boolean isDelete = userService.disableAccount(userId, password); // 서비스의 회원탈퇴 기능을 호출

        if(isDelete) {
            return ResponseEntity.ok("회원탈퇴가 정상적으로 진행되었습니다.");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호가 일치하지 않습니다.");
        }
    }

}
