package com.example.demo.mypage.domain;

import com.example.demo.movie.domain.Movie;
import com.example.demo.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "movie_wishlist",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "movie_id"})
    }
)
@Getter
@Setter
public class MovieWishlist {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wishlist_seq_gen")
  @SequenceGenerator(name = "wishlist_seq_gen", sequenceName = "SEQ_WISHLIST_ID", allocationSize = 1)
  private Long wishlistId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "movie_id", nullable = false)
  private Movie movie;

  private LocalDateTime likedAt = LocalDateTime.now();
}