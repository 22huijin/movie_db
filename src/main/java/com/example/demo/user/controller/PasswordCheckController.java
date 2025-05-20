package com.example.demo.user.controller;

import com.example.demo.user.dto.PasswordCheckRequestDTO;
import com.example.demo.user.service.PasswordCheckService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
@Tag(name = "회원 관리", description = "회원가입, 회원정보 수정, 탈퇴 관련 API")
public class PasswordCheckController {

    private final PasswordCheckService passwordCheckService;  // ✅ 추가된 부분

    @Operation(summary = "비밀번호 인증", description = "회원정보 수정, 회원 탈퇴 전에 비밀번호를 인증합니다.")
    @PostMapping("/{userId}/check-password")
    public ResponseEntity<String> checkPassword(@PathVariable Long userId, @RequestBody PasswordCheckRequestDTO request) {
        boolean matches = passwordCheckService.checkPassword(userId, request.getCurrentPassword());
        if (matches) {
            return ResponseEntity.ok("비밀번호가 일치합니다.");
        } else {
            return ResponseEntity.status(401).body("비밀번호가 일치하지 않습니다.");
        }
    }
}

