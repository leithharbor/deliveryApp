package com.example.deliveryApp.user.service;

import com.example.deliveryApp.common.exception.ResponseCode;
import com.example.deliveryApp.entity.User;
import com.example.deliveryApp.user.dto.SignUpRequestDto;
import com.example.deliveryApp.user.dto.UserLoginRequestDto;
import com.example.deliveryApp.user.encoder.PasswordEncoder;
import com.example.deliveryApp.user.exception.EmailDuplicateCheckException;
import com.example.deliveryApp.user.exception.PasswordAuthFailException;
import com.example.deliveryApp.user.exception.UserDeleteAlreadyException;
import com.example.deliveryApp.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입 기능
    public void createAccount(SignUpRequestDto signUpRequestDto) {

        //리퀘스트 요청에 들어온 비밀번호와 재확인 비밀번호가 일치 하지 않을 시
        if(!signUpRequestDto.getPassword().equals(signUpRequestDto.getReEnterPassword())) {
            throw new PasswordAuthFailException();
        }

        //이메일 중복 확인 로직
        if (userRepository.existsByUserEmail(signUpRequestDto.getUserEmail())) {
            throw new EmailDuplicateCheckException();
        }

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

    // 회원탈퇴 기능
    @Transactional
    public void disableAccount(Long userId, String password) {
        User user = findById(userId);

        // 비밀번호가 불일치 할 시 반환 로직
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new PasswordAuthFailException();
        }

        //이미 탈퇴한 유저일 시 반환 로직
        if (!user.isDelete()) {
            throw new UserDeleteAlreadyException();
        }

        // 비밀번호가 일치 할 시 반환 로직
        user.setDelete(true);
        userRepository.save(user);
    }

    // 로그인 기능
    public User login(UserLoginRequestDto loginRequestDto) {
        User user = userRepository.findUserByUserEmail(loginRequestDto.getUserEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입된 정보가 없습니다 회원가입을 진행해주세요"));

        // 비밀번호 검즘
        if(!passwordEncoder.matches(loginRequestDto.getPassword(),user.getPassword())) {
            throw new PasswordAuthFailException();
        }
        return user;
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
    }
}


