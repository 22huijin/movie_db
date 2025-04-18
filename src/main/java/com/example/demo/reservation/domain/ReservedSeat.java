package com.example.demo.reservation.domain;

import com.example.demo.screen.domain.Seat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ReservedSeat {
  @Id
  @GeneratedValue
  private Long reservedSeatId;

  @ManyToOne
  @JoinColumn(name = "reservation_id")
  private Reservation reservation;

  @ManyToOne
  @JoinColumn(name = "seat_id")
  private Seat seat;

  private String status;
}