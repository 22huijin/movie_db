package com.example.demo.movie.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MovieDetail {
  @Id
  private Long movieId;

  @MapsId
  @OneToOne
  @JoinColumn(name = "movie_id")
  private Movie movie;

  private String genre;
  private String director;
  private String actors;
  private String trailerUrl;
  private String stillCutUrl;
}