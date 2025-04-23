package com.example.demo.schedule.controller;

import com.example.demo.schedule.dto.ScheduleDeleteRequestDTO;
import com.example.demo.schedule.dto.ScheduleRegisterResponseDTO;
import com.example.demo.schedule.service.ScheduleDeletionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "스케줄 삭제", description = "스케줄 및 관련 좌석상태 삭제")
@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleDeletionController {

  private final ScheduleDeletionService scheduleDeletionService;

  @DeleteMapping("/delete")
  @Operation(summary = "상영 스케줄 삭제")
  public ScheduleRegisterResponseDTO deleteSchedule(@RequestBody ScheduleDeleteRequestDTO dto) {
    return scheduleDeletionService.deleteScheduleById(dto);
  }
}