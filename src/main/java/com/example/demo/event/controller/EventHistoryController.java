package com.example.demo.event.controller;

import com.example.demo.event.dto.EventHistoryDto;
import com.example.demo.event.service.EventHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "이벤트", description = "이벤트 관련 API")
@RestController
@RequestMapping("/api/event/history")
public class EventHistoryController {

  private final EventHistoryService eventHistoryService;

  public EventHistoryController(EventHistoryService eventHistoryService) {
    this.eventHistoryService = eventHistoryService;
  }

  @Operation(summary = "이벤트 응모 이력 조회", description = "회원이 응모한 이벤트 목록과 결과를 조회합니다.")
  @GetMapping("/{userId}")
  public ResponseEntity<List<EventHistoryDto>> getEventHistory(
      @Parameter(name = "userId", description = "유저 ID", example = "1")
      @PathVariable(name = "userId") Long userId
  ) {
    List<EventHistoryDto> history = eventHistoryService.getEventHistoryByUserId(userId);
    return ResponseEntity.ok(history);
  }
}