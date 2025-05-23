package com.example.demo.user.controller;

import com.example.demo.user.dto.UserInfoResponseDTO;
import com.example.demo.user.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Tag(name = "마이페이지", description = "회원정보 조회/영화 찜/내 리뷰 모아보기 API")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Operation(summary = "회원정보 조회", description = "회원의 개인정보를 조회합니다.")
    @GetMapping("/{userId}")
    public UserInfoResponseDTO getUserInfo(@PathVariable Long userId) {
        return userInfoService.getUserInfo(userId);
    }
}
