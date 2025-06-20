package com.example.demo.event.controller;

import com.example.demo.event.dto.EventApplyRequestDto;
import com.example.demo.event.dto.EventApplyResponseDto;
import com.example.demo.event.service.EventApplyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "이벤트", description = "이벤트 관련 API")
@RestController
@RequestMapping("/api/event/apply")
public class EventApplyController {

  private final EventApplyService eventApplyService;

  public EventApplyController(EventApplyService eventApplyService) {
    this.eventApplyService = eventApplyService;
  }

  @Operation(summary = "이벤트 응모", description = "이벤트 응모를 처리합니다.")
  @PostMapping
  public ResponseEntity<EventApplyResponseDto> applyToEvent(@RequestBody EventApplyRequestDto dto) {
    EventApplyResponseDto response = eventApplyService.applyToEvent(dto);
    return ResponseEntity.ok(response);
  }
}