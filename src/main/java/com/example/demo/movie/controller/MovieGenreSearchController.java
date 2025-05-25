package com.example.demo.movie.controller;

import com.example.demo.movie.dto.MovieSimpleResponseDTO;
import com.example.demo.movie.service.MovieGenreSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "영화", description = "영화 등록 및 조회 관련 API")
@RequestMapping("/movies")
public class MovieGenreSearchController {

    private final MovieGenreSearchService movieGenreSearchService;

    @Operation(summary = "영화 장르별 조회", description = "해당 장르의 영화 목록을 반환합니다.")
    @GetMapping("/genre")
    public ResponseEntity<List<MovieSimpleResponseDTO>> getMoviesByGenre(@RequestParam String name) {
        List<MovieSimpleResponseDTO> movies = movieGenreSearchService.searchByGenre(name);
        return ResponseEntity.ok(movies);
    }
}

