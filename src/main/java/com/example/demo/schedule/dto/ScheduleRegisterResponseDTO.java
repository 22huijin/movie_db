package com.example.demo.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 기능: 스케줄 등록/삭제 API 응답용 DTO
@Getter
@AllArgsConstructor
public class ScheduleRegisterResponseDTO {
  private boolean success;
  private String message;
}