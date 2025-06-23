package com.example.demo.user.controller;

import com.example.demo.user.dto.UserUpdateRequestDTO;
import com.example.demo.user.service.UserUpdateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "마이페이지", description = "회원정보 조회/영화 찜/내 리뷰 모아보기 API")
public class UserUpdateController {

    private final UserUpdateService userService;

    @PutMapping("/{userId}")
    @Operation(summary = "회원 정보 수정", description = "닉네임과 비밀번호를 수정합니다.")
    public ResponseEntity<Void> updateUserInfo(
            @PathVariable Long userId,
            @Valid @RequestBody UserUpdateRequestDTO requestDTO) {

        userService.updateUserInfo(userId, requestDTO);
        return ResponseEntity.ok().build();
    }
}
