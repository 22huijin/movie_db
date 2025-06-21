package com.example.demo.user.controller;

import com.example.demo.user.domain.User;
import com.example.demo.user.dto.UserRejoinRequestDTO;
import com.example.demo.user.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "회원 관리", description = "회원가입, 회원정보 수정, 탈퇴 관련 API")
public class UserRejoinController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/rejoin")
    @Operation(summary = "탈퇴 회원 재가입", description = "탈퇴한 계정의 이메일과 비밀번호가 일치하면 계정을 복구합니다.")
    public ResponseEntity<?> rejoin(@RequestBody UserRejoinRequestDTO dto) {
        return userRepository.findByEmailAndWithdrawStatus(dto.getEmail(), "withdraw")
                .map(user -> {
                    if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
                        return ResponseEntity.badRequest().body("비밀번호가 일치하지 않습니다.");
                    }

                    user.setWithdrawStatus("active");
                    user.setJoinDate(LocalDate.now());
                    userRepository.save(user);

                    return ResponseEntity.ok("계정이 복구되어 재가입되었습니다.");
                })
                .orElseGet(() -> ResponseEntity.badRequest().body("탈퇴한 계정을 찾을 수 없습니다."));
    }
}

