package com.example.demo.user.controller;

import com.example.demo.user.service.UserDeleteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "회원 관리", description = "회원가입, 회원정보 수정, 탈퇴 관련 API")
public class UserDeleteController {

    private final UserDeleteService userDeleteService;

    @Operation(summary = "탈퇴 처리", description = "탈퇴를 요청한 회원에 대하여, User 테이블의 status를 withdraw로 변경합니다.")
    @DeleteMapping("/{userId}/withdraw")
    public ResponseEntity<String> withdraw(@PathVariable Long userId) {
        userDeleteService.withdrawUser(userId);
        return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
    }
}

