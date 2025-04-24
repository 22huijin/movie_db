package com.example.demo.reservation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
public class SeatSelectionRequestDTO {
  @Schema(description = "사용자 ID", example = "1", required = true)
  private Long userId;

  @Schema(description = "스케줄 ID", example = "1", required = true)
  private Long scheduleId;

  @Schema(description = "선택 좌석 리스트", required = true)
  private List<SeatInfo> seats;

  @Getter
  public static class SeatInfo {
    @Schema(description = "행 좌석 (char)", example = "A", required = true)
    private char rowNo;

    @Schema(description = "열 좌석 (int)", example = "5", required = true)
    private int colNo;

    @Schema(description = "사용자 타입", example = "Adult", required = true)
    private String userType;
  }
}