package com.example.demo.user.controller;

import com.example.demo.user.service.UserDeleteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Tag(name = "회원탈퇴", description = "입력받은 User_id와 일치하는 데이터 삭제 API")
public class UserDeleteController {

    private final UserDeleteService userDeleteService;

    public UserDeleteController(UserDeleteService userDeleteService) {
        this.userDeleteService = userDeleteService;
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userDeleteService.deleteUserById(userId);
        return ResponseEntity.ok("탈퇴 처리되었습니다.");
    }
}
