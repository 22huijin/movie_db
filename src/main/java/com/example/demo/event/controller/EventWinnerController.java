package com.example.demo.event.controller;

import com.example.demo.event.dto.EventWinnerDto;
import com.example.demo.event.service.EventWinnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "이벤트", description = "이벤트 관련 API")
@RestController
@RequestMapping("/api/event/winners")
public class EventWinnerController {

  private final EventWinnerService eventWinnerService;

  public EventWinnerController(EventWinnerService eventWinnerService) {
    this.eventWinnerService = eventWinnerService;
  }

  @Operation(summary = "이벤트 당첨자 조회", description = "이벤트 ID로 당첨자 명단을 조회합니다.")
  @GetMapping("/{eventId}")
  public ResponseEntity<List<EventWinnerDto>> getWinners(
      @Parameter(name = "eventId", description = "이벤트 ID", example = "1")
      @PathVariable(name = "eventId") Long eventId
  ) {
    List<EventWinnerDto> winners = eventWinnerService.getWinnersByEventId(eventId);
    return ResponseEntity.ok(winners);
  }
}