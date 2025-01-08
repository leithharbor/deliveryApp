package com.example.deliveryApp.user.service;

import com.example.deliveryApp.entity.User;
import com.example.deliveryApp.user.dto.SignUpRequestDto;
import com.example.deliveryApp.user.encoder.PasswordEncoder;
import com.example.deliveryApp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //회원 가입 기능
    public void userSignUp(SignUpRequestDto signUpRequestDto) {

        //리퀘스트에 들어온 비밀번호를 bcrypt로 인코딩
        String bcryptPassword = passwordEncoder.encode(signUpRequestDto.getPassword());
        //user 양식에 맞춰 리퀘스트의 값을 받은 후
        User user = new User(
                signUpRequestDto.getUserEmail(),
                bcryptPassword,
                signUpRequestDto.getUserName(),
                signUpRequestDto.getUserType());
        //userRepository 의 save 기능을 사용해서 저장
        userRepository.save(user);
    }


}
