package com.example.demo.screen.domain;

import com.example.demo.reservation.domain.ReservedSeat;
import com.example.demo.reservation.domain.SeatLock;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "seats")
@Getter
@Setter
public class Seat {
  @Id
  @GeneratedValue
  private Long seatId;

  @ManyToOne
  @JoinColumn(name = "screen_id")
  private Screen screen;

  private char row_no;
  private int col_no;

  @OneToMany(mappedBy = "seat")
  private List<ReservedSeat> reservedSeats;

  @OneToMany(mappedBy = "seat")
  private List<SeatLock> seatLocks;
}