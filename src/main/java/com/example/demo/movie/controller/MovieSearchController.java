package com.example.demo.movie.controller;

import com.example.demo.movie.dto.MovieSearchResponseDTO;
import com.example.demo.movie.service.MovieSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieSearchController {

    private final MovieSearchService movieSearchService;

    public MovieSearchController(MovieSearchService movieSearchService) {
        this.movieSearchService = movieSearchService;
    }

    // 예: /movies/search?keyword=어벤져스
    @GetMapping("/movies/search")
    public List<MovieSearchResponseDTO> searchMovies(@RequestParam String keyword) {
        return movieSearchService.searchMoviesByTitle(keyword);
    }
}

