package com.example.demo.movie.service;

import com.example.demo.movie.domain.Movie;
import com.example.demo.movie.repository.MovieRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieCloseService {

    private final MovieRepository movieRepository;

    public void markAsEnded(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found with ID: " + movieId));

        movie.setStatus("ENDED");
        movieRepository.save(movie);
    }
}

