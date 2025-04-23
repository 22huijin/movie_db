package com.example.demo.schedule.controller;

import com.example.demo.schedule.dto.ScheduleRegisterRequestDTO;
import com.example.demo.schedule.dto.ScheduleRegisterResponseDTO;
import com.example.demo.schedule.service.ScheduleRegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "상영 스케줄 등록", description = "영화와 상영관 정보를 기반으로 상영스케줄을 등록합니다.")
@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleRegistrationController {

  private final ScheduleRegistrationService scheduleRegistrationService;

  @PostMapping("/register")
  @Operation(summary = "상영 스케줄 등록")
  public ScheduleRegisterResponseDTO register(@RequestBody ScheduleRegisterRequestDTO dto) {
    return scheduleRegistrationService.registerSchedule(dto);
  }
}