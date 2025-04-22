package com.example.demo.schedule.domain;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ScheduleSeatId implements Serializable {
  private Integer scheduleId;
  private Integer seatId;
}
