package com.example.demo.reservation.domain;

import com.example.demo.screen.domain.Seat;
import com.example.demo.schedule.domain.Schedule;
import com.example.demo.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class SeatLock {
  @Id
  @GeneratedValue
  private Long lockId;

  @ManyToOne
  @JoinColumn(name = "seat_id")
  private Seat seat;

  @ManyToOne
  @JoinColumn(name = "showtime_id")
  private Schedule schedule;

  @ManyToOne(optional = true)
  @JoinColumn(name = "user_id")
  private User user;

  private LocalDateTime lockedAt;
  private LocalDateTime expiresAt;
}