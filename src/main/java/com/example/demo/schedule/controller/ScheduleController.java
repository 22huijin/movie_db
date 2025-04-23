// 기능: 프론트에서 전달된 날짜로 상영시간표 리스트 조회
package com.example.demo.schedule.controller;

import com.example.demo.schedule.dto.ScheduleResponseDTO;
import com.example.demo.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

  private final ScheduleService scheduleService;

  // 날짜 파라미터 형식: yyyy-MM-dd
  @GetMapping
  public List<ScheduleResponseDTO> getSchedulesByDate(
      @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
  ) {
    return scheduleService.getScheduleListByDate(date);
  }
}