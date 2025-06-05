package com.example.demo.movie.controller;

import com.example.demo.movie.dto.MovieSimpleResponseDTO;
import com.example.demo.movie.service.MovieSimpleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "영화", description = "영화 등록 및 조회 관련 API")
@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieSimpleController {
    private final MovieSimpleService movieSimpleService;

    @GetMapping("/simple")
    @Operation(summary = "상영작/예정작 조회", description = "영화의 상영 상태(SHOWING/UPCOMING), 장르, 상영등급을 기반으로 영화 목록을 조회합니다.")
    public List<MovieSimpleResponseDTO> getMoviesByFilters(
            @RequestParam String status,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String ageRating
    ) {
        return movieSimpleService.getMoviesFiltered(status, genre, ageRating);
    }
}
