package com.example.demo.schedule.domain;

import com.example.demo.movie.domain.Movie;
import com.example.demo.screen.domain.Screen;
import com.example.demo.reservation.domain.Reservation;
import com.example.demo.reservation.domain.SeatLock;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Schedule {
  @Id
  @GeneratedValue
  private Long scheduleId;

  @ManyToOne
  @JoinColumn(name = "movie_id")
  private Movie movie;

  @ManyToOne
  @JoinColumn(name = "screen_id")
  private Screen screen;

  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private int availableSeats;
  private int price;

  @OneToMany(mappedBy = "schedule")
  private List<Reservation> reservations;

  @OneToMany(mappedBy = "schedule")
  private List<SeatLock> seatLocks;
}