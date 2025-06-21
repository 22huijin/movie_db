package com.example.demo.movie.controller;

import com.example.demo.movie.dto.MovieSimpleResponseDTO;
import com.example.demo.movie.service.MovieSimpleSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
@Tag(name = "영화", description = "영화 조회 API")
public class MovieSimpleSearchController {

    private final MovieSimpleSearchService movieService;

    @Operation(summary = "영화 ID로 영화 기본정보 조회", description = "영화 ID로 영화 기본정보 조회")
    @GetMapping("/{movieId}")
    public ResponseEntity<MovieSimpleResponseDTO> getMovieById(@PathVariable Long movieId) {
        MovieSimpleResponseDTO dto = movieService.getMovieById(movieId);
        return ResponseEntity.ok(dto);
    }
}
