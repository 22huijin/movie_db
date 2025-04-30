package com.example.demo.movie.controller;

import com.example.demo.movie.dto.MovieDetailResponseDTO;
import com.example.demo.movie.service.MovieDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movie-details")
@RequiredArgsConstructor
@Tag(name = "영화 상세 정보 조회 API", description = "영화 상세 정보 조회 API")
public class MovieDetailController {

    private final MovieDetailService movieDetailService;

    @Operation(summary = "영화 상세 정보 조회", description = "영화 ID를 통해 상세 정보를 반환합니다.")
    @GetMapping("/{movieId}")
    public MovieDetailResponseDTO getMovieDetail(
            @Parameter(description = "영화 ID", example = "1") @PathVariable Long movieId) {
        return movieDetailService.getMovieDetailById(movieId);
    }
}

