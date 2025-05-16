package com.example.demo.reservation.domain;

import com.example.demo.schedule.domain.ScheduleSeat;
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
  @SequenceGenerator(
      name = "seat_lock_seq",
      sequenceName = "seat_lock_seq",
      allocationSize = 1
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "seat_lock_seq"
  )
  private Long lockId;

  @MapsId("scheduleSeatId")
  @ManyToOne
  @JoinColumns({
      @JoinColumn(name = "schedule_id", referencedColumnName = "schedule_id"),
      @JoinColumn(name = "seat_id", referencedColumnName = "seat_id")
  })
  private ScheduleSeat scheduleSeat;

  @ManyToOne(optional = true)
  @JoinColumn(name = "user_id")
  private User user;

  private LocalDateTime lockedAt;
  private LocalDateTime expiresAt;
}