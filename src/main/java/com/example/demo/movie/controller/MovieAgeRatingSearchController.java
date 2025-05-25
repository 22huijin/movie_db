package com.example.demo.movie.controller;

import com.example.demo.movie.dto.MovieSimpleResponseDTO;
import com.example.demo.movie.service.MovieAgeRatingSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
@Tag(name = "영화", description = "영화 등록 및 조회 관련 API")
public class MovieAgeRatingSearchController {
    private final MovieAgeRatingSearchService movieAgeRatingSearchService;

    public MovieAgeRatingSearchController(MovieAgeRatingSearchService movieAgeRatingSearchService) {
        this.movieAgeRatingSearchService = movieAgeRatingSearchService;
    }

    @Operation(summary = "영화 상영등급별 조회", description = "상영등급(string)을 입력받아 일치하는 영화를 반환합니다.")
    @GetMapping("/age-rating")
    public ResponseEntity<List<MovieSimpleResponseDTO>> getMoviesByAgeRating(@RequestParam String rating) {
        List<MovieSimpleResponseDTO> movies = movieAgeRatingSearchService.searchByAgeRating(rating);
        return ResponseEntity.ok(movies);
    }
}
