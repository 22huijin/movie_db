package com.example.demo.event.domain;

import com.example.demo.movie.domain.Movie;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "events")
@Getter
@Setter
public class Event {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq_gen")
  @SequenceGenerator(name = "event_seq_gen", sequenceName = "EVENT_SEQ", allocationSize = 1)
  private Long eventId;

  private String eventName;

  private LocalDate eventStart;

  private LocalDate eventEnd;

  private String eventThumbnailUrl;

  @ManyToOne
  @JoinColumn(name = "movie_id", nullable = false)
  private Movie movie;

  private Integer maxWinners;
}