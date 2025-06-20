package com.example.demo.event.controller;

import com.example.demo.event.dto.EventSelectResponseDto;
import com.example.demo.event.service.EventSelectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "이벤트", description = "이벤트 관련 API")
@RestController
@RequestMapping("/api/event/select")
public class EventSelectController {

  private final EventSelectService eventSelectService;

  public EventSelectController(EventSelectService eventSelectService) {
    this.eventSelectService = eventSelectService;
  }

  @Operation(summary = "이벤트 당첨자 추첨", description = "이벤트 종료 후 랜덤으로 당첨자를 선정합니다.")
  @PostMapping("/{eventId}")
  public ResponseEntity<EventSelectResponseDto> selectWinners(
      @Parameter(name = "eventId", description = "이벤트 ID", example = "1")
      @PathVariable(name = "eventId") Long eventId
  ) {
    EventSelectResponseDto response = eventSelectService.selectWinners(eventId);
    return ResponseEntity.ok(response);
  }
}