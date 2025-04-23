package com.example.demo.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@AllArgsConstructor
public class ScheduleSeatResponseDTO {
  @Schema(description = "좌석 행 (영문 대문자)", example = "A")
  private char rowNo;

  @Schema(description = "좌석 열 (숫자)", example = "7")
  private int colNo;

  @Schema(description = "좌석 상태 (AVAILABLE / RESERVED)", example = "AVAILABLE")
  private String status;

}