package com.example.demo.movie.controller;

import com.example.demo.movie.dto.MovieSearchResponseDTO;
import com.example.demo.movie.service.MovieSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@Tag(name = "영화", description = "영화 등록 및 조회 관련 API")
public class MovieSearchController {

    private final MovieSearchService movieSearchService;

    public MovieSearchController(MovieSearchService movieSearchService) {
        this.movieSearchService = movieSearchService;
    }
    @Operation(summary = "영화 기본 정보 조회", description = "키워드를 입력받아 해당 키워드가 제목에 포함된 영화를 반환합니다.")
    // 예: /movies/search?keyword=어벤져스
    @GetMapping("/search")
    public List<MovieSearchResponseDTO> searchMovies(@RequestParam String keyword) {
        return movieSearchService.searchMoviesByTitle(keyword);
    }
}

