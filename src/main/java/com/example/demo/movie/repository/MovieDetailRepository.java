package com.example.demo.movie.repository;

import com.example.demo.movie.domain.MovieDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDetailRepository extends JpaRepository<MovieDetail, Long> {
}