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

    public List<MovieSimpleResponseDTO> getMoviesFiltered(String status, String genre, String ageRating) {
        List<Movie> movies = movieRepository.findByStatus(status);

        return movies.stream()
                .filter(movie -> genre == null || (
                        movie.getMovieDetail() != null &&
                                movie.getMovieDetail().getGenre() != null &&
                                movie.getMovieDetail().getGenre().contains(genre)
                ))
                .filter(movie -> ageRating == null || (
                        movie.getAgeRating() != null &&
                                movie.getAgeRating().equals(ageRating)
                ))
                .map(MovieSimpleResponseDTO::fromEntity) // ✅ 변경된 부분
                .collect(Collectors.toList());
    }
}
