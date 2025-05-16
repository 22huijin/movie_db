package com.example.demo.screen.controller;

import com.example.demo.screen.dto.ScreenDeleteRequestDTO;
import com.example.demo.screen.dto.ScreenRegisterResponseDTO;
import com.example.demo.screen.service.ScreenDeletionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "상영관", description = "상영관 및 좌석 API")
@RestController
@RequestMapping("/api/screens")
@RequiredArgsConstructor
public class ScreenDeletionController {

  private final ScreenDeletionService screenDeletionService;

  @Operation(summary = "상영관 삭제", description = "상영관 이름을 기반으로 해당 상영관과 관련 좌석 데이터를 삭제합니다.")
  @DeleteMapping("/delete")
  public ScreenRegisterResponseDTO deleteScreen(@RequestBody ScreenDeleteRequestDTO dto) {
    return screenDeletionService.deleteScreenByName(dto);
  }
}