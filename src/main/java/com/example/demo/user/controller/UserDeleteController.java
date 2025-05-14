package com.example.demo.user.controller;

import com.example.demo.user.service.UserDeleteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "회원 탈퇴 API", description = "회원 탈퇴 처리")
public class UserDeleteController {

    private final UserDeleteService userDeleteService;

    @DeleteMapping("/{userId}/withdraw")
    public ResponseEntity<String> withdraw(@PathVariable Long userId) {
        userDeleteService.withdrawUser(userId);
        return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
    }
}
