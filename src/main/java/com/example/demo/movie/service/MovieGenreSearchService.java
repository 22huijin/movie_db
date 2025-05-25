package com.example.demo.movie.service;

import com.example.demo.movie.domain.Movie;
import com.example.demo.movie.dto.MovieSimpleResponseDTO;
import com.example.demo.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieGenreSearchService {

    private final MovieRepository movieRepository;

    public List<MovieSimpleResponseDTO> searchByGenre(String genre) {
        return movieRepository.findByGenreContaining(genre).stream()
                .map(MovieSimpleResponseDTO::fromEntity)
                .toList();
    }


}

