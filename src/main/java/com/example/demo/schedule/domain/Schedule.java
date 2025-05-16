package com.example.demo.schedule.domain;

import com.example.demo.movie.domain.Movie;
import com.example.demo.screen.domain.Screen;
import com.example.demo.reservation.domain.Reservation;
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
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "schedule_seq_gen")
  @SequenceGenerator(name = "schedule_seq_gen", sequenceName = "SCHEDULE_SEQ", allocationSize = 1)
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
//  private int price;
}