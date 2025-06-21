package com.example.demo.user.controller;

import com.example.demo.user.dto.EmailCheckRequestDTO;
import com.example.demo.user.dto.EmailCheckResponseDTO;
import com.example.demo.user.service.EmailDuplicateCheckService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "회원 관리", description = "회원가입, 회원정보 수정, 탈퇴 관련 API")
public class EmailDuplicateCheckController {

    private final EmailDuplicateCheckService emailDuplicateCheckService;

    @Operation(summary = "이메일 중복 검사", description = "회원가입 전에 해당 이메일로 이미 가입된 정보가 있는지 조회합니다.")
    @PostMapping("/check-email")
    public EmailCheckResponseDTO checkEmailDuplicate(@RequestBody EmailCheckRequestDTO requestDto) {
        return emailDuplicateCheckService.checkEmail(requestDto.getEmail());
    }
}

