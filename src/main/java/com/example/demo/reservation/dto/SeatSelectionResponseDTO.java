package com.example.demo.reservation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@Schema(description = "좌석 선택 및 요금 계산 결과")
public class SeatSelectionResponseDTO {
  @Schema(description = "성공 여부", example = "true")
  private boolean success;

  @Schema(description = "메시지")
  private String message;

  @Schema(description = "lockId 와 price 리스트")
  private List<LockPriceDTO> reservations;
}