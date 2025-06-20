package com.example.demo.user.controller;

import com.example.demo.user.dto.UserUpdateRequestDTO;
import com.example.demo.user.service.UserUpdateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@Tag(name = "회원 관리", description = "회원가입, 회원정보 수정, 탈퇴 관련 API")
public class UserUpdateController {
    private final UserUpdateService userUpdateService;

    public UserUpdateController(UserUpdateService userUpdateService) {
        this.userUpdateService = userUpdateService;
    }

    @Operation(summary = "회원정보 수정", description = "회원의 자신의 정보를 수정합니다.")
    @PutMapping("/update")
    public ResponseEntity<String> updateUser(
            @RequestBody UserUpdateRequestDTO dto,
            HttpSession session
    ) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        userUpdateService.updateUser(userId, dto);
        return ResponseEntity.ok("회원정보가 수정되었습니다.");
    }

}