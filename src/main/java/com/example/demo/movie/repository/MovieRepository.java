package com.example.demo.movie.repository;

import com.example.demo.movie.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
  Optional<Movie> findByTitle(String title);
  List<Movie> findByStatus(String status);
  List<Movie> findByTitleContaining(String keyword);
  List<Movie> findByMovieDetailGenre(String genre);
  @Query("SELECT m FROM Movie m JOIN m.movieDetail d WHERE d.genre LIKE %:genre%")
  List<Movie> findByGenreContaining(@Param("genre") String genre);
  List<Movie> findByAgeRating(String ageRating);
}