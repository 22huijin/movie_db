package com.example.demo.schedule.controller;

import com.example.demo.schedule.dto.ScheduleSeatWithPricingResponseDTO;
import com.example.demo.schedule.service.ScheduleSeatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "상영일정", description = "상영일정 관련 API")
@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleSeatController {

  private final ScheduleSeatService scheduleSeatService;

  @Operation(summary = "좌석 + 가격 정보 조회", description = "선택된 상영스케줄을 기반으로 해당 상영관의 좌석과 가격 데이터를 조회합니다.")
  @GetMapping("/seats")
  public ScheduleSeatWithPricingResponseDTO getSeatsBySchedule(
      @Parameter(description = "조회할 상영 일정 ID", example = "1")
      @RequestParam("schedule_id") Long scheduleId
  ) {
    return scheduleSeatService.getSeatsAndPricingBySchedule(scheduleId);
  }
}