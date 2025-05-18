package com.example.demo.schedule.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ScheduleSeatId implements Serializable {

  private Long scheduleId;
  private Long seatId;

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
