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

    private final MovieSimpleService movieService;

    @GetMapping("/simple")
    @Operation(summary = "상영작/예정작 조회", description = "영화의 상영 상태(SHOWING/UPCOMING)에 따라 목록을 조회합니다.")
    public List<MovieSimpleResponseDTO> getMoviesByStatus(@RequestParam String status) {
        return movieService.getMoviesByStatus(status);
    }
}
