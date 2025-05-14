package com.example.demo.movie.service;

import com.example.demo.movie.domain.Movie;
import com.example.demo.movie.dto.MovieSimpleResponseDTO;
import com.example.demo.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieSimpleService {

    private final MovieRepository movieRepository;

    public List<MovieSimpleResponseDTO> getMoviesByStatus(String status) {
        List<Movie> movies = movieRepository.findByStatus(status);
        return movies.stream()
                .map(movie -> new MovieSimpleResponseDTO(
                        movie.getMovieId(),
                        movie.getTitle(),
                        movie.getThumbnailUrl(),
                        movie.getRunningTime(),
                        movie.getReleaseDate(),
                        movie.getStatus(),
                        movie.getLikeRating()
                ))
                .collect(Collectors.toList());
    }
}
