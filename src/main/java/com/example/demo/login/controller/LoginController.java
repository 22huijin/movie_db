package com.example.demo.login.controller;

import com.example.demo.login.dto.LoginRequestDTO;
import com.example.demo.login.dto.LoginResponseDTO;
import com.example.demo.login.service.LoginService;
import com.example.demo.user.domain.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Tag(name = "로그인", description = "이메일/비밀번호 입력받아 로그인하는 API")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService LoginService) {
        this.loginService = LoginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto, HttpSession session) {
        User user = loginService.login(dto.getEmail(), dto.getPassword());
        session.setAttribute("loginUser", user.getUserId());
        session.setAttribute("role", user.getRole());
        return ResponseEntity.ok(new LoginResponseDTO(user));
    }

    @GetMapping("/me") // 현재 로그인된 사용자 정보 조회
    public ResponseEntity<LoginResponseDTO> me(HttpSession session) {
        Long userId = (Long) session.getAttribute("loginUser");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = loginService.findById(userId);
        return ResponseEntity.ok(new LoginResponseDTO(user));
    }
}
