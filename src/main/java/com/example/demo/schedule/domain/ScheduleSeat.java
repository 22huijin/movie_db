package com.example.demo.schedule.domain;

import com.example.demo.screen.domain.Seat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "SCHEDULE_SEAT")
public class ScheduleSeat {
  @EmbeddedId
  private ScheduleSeatId id;//schedule_seat_id

  @ManyToOne
  @MapsId("scheduleId")
  @JoinColumn(name = "schedule_id")
  private Schedule schedule;

  @ManyToOne
  @MapsId("seatId")
  @JoinColumn(name = "seat_id")
  private Seat seat;

  private String status;
}