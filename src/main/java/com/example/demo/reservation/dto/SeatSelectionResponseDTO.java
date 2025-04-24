package com.example.demo.reservation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@Schema(description = "좌석 선택 결과")
public class SeatSelectionResponseDTO {
  @Schema(description = "성공 여부", example = "true")
  private boolean success;

  @Schema(description = "메시지")
  private String message;

  @Schema(description = "생성된 lock ID 리스트")
  private List<Long> lockIds;
}