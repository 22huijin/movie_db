// 기능: 프론트에서 전달된 날짜로 상영시간표 리스트 조회
package com.example.demo.schedule.controller;

import com.example.demo.schedule.dto.ScheduleResponseDTO;
import com.example.demo.schedule.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "상영스케줄 조회", description = "상영일정 조회 API")
@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

  private final ScheduleService scheduleService;

  // 날짜 파라미터 형식: yyyy-MM-dd
  @Operation(summary = "상영스케줄 조회", description = "선택된 날짜를 기반으로 해당 일자에 등록된 영화 및 상영관 데이터를 전송합니다.")
  @GetMapping
  public List<ScheduleResponseDTO> getSchedulesByDate(
      @Parameter(description = "조회할 상영일자", example = "2025-05-01")
      @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
  ) {
    return scheduleService.getScheduleListByDate(date);
  }
}