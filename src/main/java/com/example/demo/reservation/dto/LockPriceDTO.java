package com.example.demo.reservation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "잠금된 좌석의 lockId 와 금액 정보")
public class LockPriceDTO {
  @Schema(description = "잠금된 레코드 ID")
  private Long lockId;

  @Schema(description = "해당 좌석 요금")
  private Integer price;
}