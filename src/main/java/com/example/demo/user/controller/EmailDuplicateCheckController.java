package com.example.demo.user.controller;

import com.example.demo.user.dto.EmailCheckRequestDTO;
import com.example.demo.user.dto.EmailCheckResponseDTO;
import com.example.demo.user.service.EmailDuplicateCheckService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "이메일 중복 검사", description = "이메일 중복 검사 API")
public class EmailDuplicateCheckController  {

    private final EmailDuplicateCheckService emailDuplicateCheckService;

    @PostMapping("/check-email")
    public EmailCheckResponseDTO checkEmailDuplicate(@RequestBody EmailCheckRequestDTO requestDto) {
        boolean isDuplicate = emailDuplicateCheckService.isEmailDuplicate(requestDto.getEmail());
        return new EmailCheckResponseDTO(isDuplicate);
    }
}

