package com.example.demo.schedule.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ScheduleDeleteRequestDTO {

  @Schema(description = "삭제할 스케줄 ID", example = "5")
  private Long scheduleId;
}