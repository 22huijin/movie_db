package com.example.demo.screen.domain;

import com.example.demo.schedule.domain.ScheduleSeat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "seat")
@Getter
@Setter
public class Seat {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seat_seq_gen")
  @SequenceGenerator(name = "seat_seq_gen", sequenceName = "SEAT_SEQ", allocationSize = 1)
  private Long seatId;

  @ManyToOne
  @JoinColumn(name = "screen_id")
  private Screen screen;//id

  private char row_no;
  private int col_no;

  @OneToMany(mappedBy = "seat")
  private List<ScheduleSeat> reservedSeats;
}