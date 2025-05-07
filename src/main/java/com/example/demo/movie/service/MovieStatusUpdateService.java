package com.example.demo.movie.service;

import com.example.demo.movie.domain.Movie;
import com.example.demo.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovieStatusUpdateService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieStatusUpdateService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void updateMovieStatus(LocalDate today) {
        List<Movie> movies = movieRepository.findAll();

        for (Movie movie : movies) {
            if (!today.isBefore(movie.getReleaseDate()) && "상영예정".equals(movie.getStatus())) {
                movie.setStatus("상영중");
                movieRepository.save(movie);
            }
        }
    }
}



