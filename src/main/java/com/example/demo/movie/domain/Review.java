package com.example.demo.movie.domain;

import com.example.demo.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Review {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq_gen")
  @SequenceGenerator(name = "review_seq_gen", sequenceName = "REVIEW_SEQ", allocationSize = 1)
  private Long reviewId;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "movie_id")
  private Movie movie;

  private float rating;

  private String context;
}