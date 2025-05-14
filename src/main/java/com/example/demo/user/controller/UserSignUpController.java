package com.example.demo.user.controller;

import com.example.demo.user.dto.UserSignUpRequestDTO;
import com.example.demo.user.dto.UserSignUpResponseDTO;
import com.example.demo.user.service.UserSignUpService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "회원가입", description = "회원 정보 입력받아 테이블에 insert하는 API")
public class UserSignUpController {

    private final UserSignUpService signupService;

    @PostMapping("/signup")
    public UserSignUpResponseDTO signup(@RequestBody UserSignUpRequestDTO requestDto) {
        return signupService.signup(requestDto);
    }
}
