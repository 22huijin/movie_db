package com.example.demo.schedule.controller;

import com.example.demo.schedule.dto.ScheduleDeleteRequestDTO;
import com.example.demo.schedule.dto.ScheduleRegisterResponseDTO;
import com.example.demo.schedule.service.ScheduleDeletionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "상영스케줄 삭제", description = "상영스케줄 및 관련 좌석 삭제 API")
@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleDeletionController {

  private final ScheduleDeletionService scheduleDeletionService;

  @DeleteMapping("/delete")
  @Operation(summary = "상영 스케줄 삭제", description = "상영스케줄 id를 기반으로 해당 상영스케줄과 관련 좌석 데이터를 삭제합니다.")
  public ScheduleRegisterResponseDTO deleteSchedule(@RequestBody ScheduleDeleteRequestDTO dto) {
    return scheduleDeletionService.deleteScheduleById(dto);
  }
}