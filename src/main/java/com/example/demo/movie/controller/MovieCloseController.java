package com.example.demo.movie.controller;

import com.example.demo.movie.service.MovieCloseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
@Tag(name = "영화", description = "영화 등록 및 조회 관련 API")
public class MovieCloseController {

    private final MovieCloseService movieCloseService;

    @Operation(summary = "영화 상영종료", description = "movieId를 입력받아 해당 영화의 status를 ENDED로 변경합니다.")
    @PatchMapping("/{movieId}/status/end")
    public ResponseEntity<String> markMovieAsEnded(@PathVariable Long movieId) {
        movieCloseService.markAsEnded(movieId);
        return ResponseEntity.ok("영화가 상영종료 처리되었습니다.");
    }
}
