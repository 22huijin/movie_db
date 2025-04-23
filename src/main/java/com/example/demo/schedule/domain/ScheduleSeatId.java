package com.example.demo.schedule.domain;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ScheduleSeatId implements Serializable {

  private Long scheduleId;
  private Long seatId;

  // 필수: 기본 생성자
  public ScheduleSeatId() {}

  public ScheduleSeatId(Long scheduleId, Long seatId) {
    this.scheduleId = scheduleId;
    this.seatId = seatId;
  }

  // equals override
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ScheduleSeatId that)) return false;
    return Objects.equals(scheduleId, that.scheduleId) &&
        Objects.equals(seatId, that.seatId);
  }

  // hashCode override
  @Override
  public int hashCode() {
    return Objects.hash(scheduleId, seatId);
  }
}
