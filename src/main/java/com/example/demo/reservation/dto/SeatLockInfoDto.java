package com.example.demo.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SeatLockInfoDto {
  private String movieTitle;
  private String screenName;
  private LocalDateTime startTime;
  private char rowNo;
  private int colNo;
  private LocalDateTime expiresAt;
  private int price;
  private Long lockId;
}