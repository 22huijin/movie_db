package com.example.demo.schedule.domain;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ScheduleSeatId implements Serializable {
  private Long scheduleId;
  private Long seatId;

  public ScheduleSeatId(Long scheduleId, Long seatId) {
    this.scheduleId = scheduleId;
    this.seatId = seatId;
  }

  // 기본 생성자도 필요
  public ScheduleSeatId() {}
}
