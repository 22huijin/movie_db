package com.example.demo.movie.repository;

import com.example.demo.movie.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMovie_MovieId(Long movieId);
    List<Review> findByUserUserId(Long userId);
}
