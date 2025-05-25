package com.example.demo.movie.service;

import com.example.demo.movie.domain.Movie;
import com.example.demo.movie.dto.MovieSimpleResponseDTO;
import com.example.demo.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieAgeRatingSearchService {
    private final MovieRepository movieRepository;

    public List<MovieSimpleResponseDTO> searchByAgeRating(String ageRating) {
        List<Movie> movies = movieRepository.findByAgeRating(ageRating);
        return movies.stream()
                .map(MovieSimpleResponseDTO::fromEntity)
                .toList();
    }
}






