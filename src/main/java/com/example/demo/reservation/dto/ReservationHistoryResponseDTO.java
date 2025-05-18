package com.example.demo.reservation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(description = "예매 내역 응답 DTO")
public class ReservationHistoryResponseDTO {

  @Schema(description = "예매 ID", example = "1001")
  private Long reservationId;

  @Schema(description = "영화 제목", example = "Interstellar")
  private String movieTitle;

  @Schema(description = "상영관 이름", example = "Screen 1")
  private String screenName;

  @Schema(description = "상영 시작 시간", example = "2025-05-18T15:00:00")
  private LocalDateTime startTime;

  @Schema(description = "좌석 행", example = "A")
  private String rowNo;

  @Schema(description = "좌석 열 번호", example = "7")
  private int colNo;

  @Schema(description = "예매 갱신 시간", example = "2025-05-10T12:34:56")
  private LocalDateTime updateTime;

  @Schema(description = "예매 상태", example = "CONFIRMED or CANCEL")
  private String status;
}