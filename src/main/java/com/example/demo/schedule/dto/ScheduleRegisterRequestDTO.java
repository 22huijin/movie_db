package com.example.demo.schedule.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleRegisterRequestDTO {

  @Schema(description = "영화 제목", example = "이웃집 토토로")
  private String movieTitle;

  @Schema(description = "상영관 이름", example = "4관")
  private String screenName;

  @Schema(description = "상영 시작 시간", example = "2025-05-02T14:00:00")
  private LocalDateTime startTime;

  @Schema(description = "상영 종료 시간", example = "2025-05-02T16:00:00")
  private LocalDateTime endTime;

  @Schema(description = "가격", example = "13000")
  private int price;
}