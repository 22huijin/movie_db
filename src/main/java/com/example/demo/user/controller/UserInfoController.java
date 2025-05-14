package com.example.demo.user.controller;

import com.example.demo.user.dto.UserInfoResponseDTO;
import com.example.demo.user.service.UserInfoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Tag(name = "회원정보 조회", description = "회원정보 조회 API")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/{userId}")
    public UserInfoResponseDTO getUserInfo(@PathVariable Long userId) {
        return userInfoService.getUserInfo(userId);
    }
}
