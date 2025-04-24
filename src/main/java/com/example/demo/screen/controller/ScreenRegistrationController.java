package com.example.demo.screen.controller;

import com.example.demo.screen.dto.ScreenRegisterRequestDTO;
import com.example.demo.screen.dto.ScreenRegisterResponseDTO;
import com.example.demo.screen.service.ScreenRegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "상영관 등록", description = "상영관 및 좌석 등록 API")
@RestController
@RequestMapping("/api/screens")
@RequiredArgsConstructor
public class ScreenRegistrationController {

  private final ScreenRegistrationService screenRegistrationService;

  @Operation(summary = "상영관 및 좌석 등록", description = "상영관명과 좌석 리스트를 받아 새 상영관과 좌석을 등록합니다.")
  @PostMapping("/register")
  public ScreenRegisterResponseDTO registerScreen(@RequestBody ScreenRegisterRequestDTO dto) {
    return screenRegistrationService.registerScreen(dto);
  }
}