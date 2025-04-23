package com.example.demo.movie.domain;

import com.example.demo.schedule.domain.Schedule;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Movie {
  @Id
  @GeneratedValue
  private Long movieId;

  private String title;
  private String thumbnailUrl;
  private int runningTime;
  private LocalDate releaseDate;
  private String ageRating;

  @Lob
  private String description;
  private float likeRating;
  private int totalAudience;
  private String status;

  @OneToOne(mappedBy = "movie", cascade = CascadeType.ALL)
  private MovieDetail movieDetail;

  @OneToMany(mappedBy = "movie")
  private List<Schedule> schedules;

  @OneToMany(mappedBy = "movie")
  private List<Review> reviews;
}