package com.example.demo.user.controller;

import com.example.demo.user.dto.UserSignUpRequestDTO;
import com.example.demo.user.dto.UserSignUpResponseDTO;
import com.example.demo.user.service.UserSignUpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "회원 관리", description = "회원가입, 회원정보 수정, 탈퇴 관련 API")
public class UserSignUpController {

    private final UserSignUpService signupService;

    @Operation(summary = "회원가입", description = "닉네임, 이메일, 비밀번호, 생일을 입력받아 User 테이블에 삽입합니다.")
    @PostMapping("/signup")
    public UserSignUpResponseDTO signup(@RequestBody UserSignUpRequestDTO requestDto) {
        return signupService.signup(requestDto);
    }
}

