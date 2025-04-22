package com.example.demo.screen.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SeatStatusDto {
  private Long seatId;
  private String rowNo;
  private Integer colNo;
  private String status;
}