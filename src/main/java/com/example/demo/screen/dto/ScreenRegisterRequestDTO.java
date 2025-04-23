package com.example.demo.screen.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
public class ScreenRegisterRequestDTO {

  @Schema(description = "상영관 이름 (예: 4관)", example = "4관", required = true)
  private String screenName;

  @Schema(description = "좌석 정보 리스트", required = true)
  private List<SeatInfo> seats;

  @Getter
  public static class SeatInfo {
    @Schema(description = "좌석 행", example = "A", required = true)
    private char rowNo;

    @Schema(description = "좌석 열", example = "5", required = true)
    private int colNo;
  }
}