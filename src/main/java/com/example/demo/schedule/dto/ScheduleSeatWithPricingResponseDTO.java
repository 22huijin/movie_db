package com.example.demo.schedule.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ScheduleSeatWithPricingResponseDTO {
  @Schema(description = "좌석 리스트")
  private List<ScheduleSeatResponseDTO> seats;

  @Schema(description = "성인 요금", example = "13000")
  private int adultPrice;

  @Schema(description = "청소년 요금", example = "10000")
  private int youthPrice;

  @Schema(description = "경로 요금", example = "8000")
  private int seniorPrice;
}