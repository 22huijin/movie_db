package com.example.demo.movie.controller;

import com.example.demo.movie.dto.MovieSimpleResponseDTO;
import com.example.demo.movie.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
@Tag(name = "영화 기본 정보 조회 API", description = "영화 기본 정보 조회 API")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/status")
    @Operation(summary = "상영작/예정작 조회", description = "영화의 상영 상태(상영중/상영예정)에 따라 목록을 조회합니다.")
    public List<MovieSimpleResponseDTO> getMoviesByStatus(@RequestParam String status) {
        return movieService.getMoviesByStatus(status);
    }
}
