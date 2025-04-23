package com.example.demo.schedule.controller;

import com.example.demo.schedule.dto.ScheduleSeatResponseDTO;
import com.example.demo.schedule.service.ScheduleSeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleSeatController {

  private final ScheduleSeatService scheduleSeatService;

  // 클라이언트가 /api/schedules/seats?schedule_id=1 형식으로 요청
  @GetMapping("/seats")
  public List<ScheduleSeatResponseDTO> getSeatsBySchedule(
      @Parameter(description = "조회할 상영 일정 ID", example = "1")
      @RequestParam("schedule_id") Long scheduleId
  ) {
    return scheduleSeatService.getSeatsBySchedule(scheduleId);
  }
}