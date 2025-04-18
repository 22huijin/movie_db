package com.example.demo.movie.repository;

import com.example.demo.movie.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}