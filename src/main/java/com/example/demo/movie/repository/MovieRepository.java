package com.example.demo.movie.repository;

import com.example.demo.movie.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
  Optional<Movie> findByTitle(String title);
  List<Movie> findByStatus(String status);
}