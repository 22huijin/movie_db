package com.example.demo.movie.service;

import com.example.demo.movie.domain.Movie;
import com.example.demo.movie.dto.MovieSearchResponseDTO;
import com.example.demo.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieSearchService {

    private final MovieRepository movieRepository;

    public MovieSearchService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieSearchResponseDTO> searchMoviesByTitle(String keyword) {
        List<Movie> movies = movieRepository.findByTitleContaining(keyword);

        return movies.stream()
                .map(movie -> new MovieSearchResponseDTO(
                        movie.getMovieId(),
                        movie.getTitle(),
                        movie.getThumbnailUrl(),
                        movie.getRunningTime(),
                        movie.getReleaseDate(),
                        movie.getAgeRating(),
                        movie.getDescription(),
                        movie.getLikeRating(),
                        movie.getTotalAudience(),
                        movie.getStatus()
                ))
                .collect(Collectors.toList());
    }
}
