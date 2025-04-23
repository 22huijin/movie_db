// 기능: Schedule 정보를 프론트에 전송할 형태로 변환하는 DTO
package com.example.demo.schedule.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "상영 스케줄 응답 DTO")
public class ScheduleResponseDTO {

  @Schema(description = "스케줄 ID", example = "101")
  private Long scheduleId;

  @Schema(description = "영화 제목", example = "듄: 파트2")
  private String movieTitle;

  @Schema(description = "상영관 이름", example = "1관")
  private String screenName;

  @Schema(description = "상영 시작 시간", example = "2025-04-22T18:30:00")
  private String startTime;

  @Schema(description = "상영 종료 시간", example = "2025-04-22T20:55:00")
  private String endTime;

  @Schema(description = "잔여 좌석 수", example = "37")
  private int availableSeats;

  @Schema(description = "가격", example = "12000")
  private int price;
}