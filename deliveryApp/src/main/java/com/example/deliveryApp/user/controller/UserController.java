package com.example.deliveryApp.user.controller;

import com.example.deliveryApp.entity.User;
import com.example.deliveryApp.session.SessionConst;
import com.example.deliveryApp.user.dto.SignUpRequestDto;
import com.example.deliveryApp.user.dto.UserLoginRequestDto;
import com.example.deliveryApp.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //회원 가입 API
    @PostMapping("/signup")
    public ResponseEntity<String> createAccountAPI(@RequestBody SignUpRequestDto signUpRequestDto) {

        userService.createAccount(signUpRequestDto); // 서비스의 회원가입 기능을 호출
        return ResponseEntity.status(HttpStatus.CREATED).body("회원 가입이 완료 되었습니다.");
    }

    //회원 탈퇴 API
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> disableAccountAPI(
            @PathVariable(name = "userId") Long userId, // PathVariable 로 유저 Id 받기
            @RequestParam("password") String password) {

        userService.disableAccount(userId, password); // 서비스의 회원탈퇴 기능을 호출
        return ResponseEntity.ok("회원 탈퇴가 정상적으로 진행되었습니다.");
    }

    // 로그인 API
    @PostMapping("/login")
    public ResponseEntity<String> userLoginAPI(@Validated @RequestBody UserLoginRequestDto loginRequestDto, HttpServletRequest request) {
        User loginUser = userService.login(loginRequestDto);

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_USER_ID, loginUser.getUserId());
        session.setAttribute(SessionConst.LOGIN_USER_NAME, loginUser.getUserName());
        session.setAttribute(SessionConst.USER_IS_DELETE, loginUser.isDelete());

        return ResponseEntity.ok("성공적으로 로그인 하였습니다.");
    }

    // 로그아웃 API
    @PostMapping("/logout")
    public ResponseEntity<String> userLogoutAPI(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if(session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("성공적으로 로그아웃 했습니다.");
    }
}
