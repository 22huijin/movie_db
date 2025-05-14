package com.example.demo.movie.controller;

import com.example.demo.movie.service.MovieCloseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
@Tag(name = "영화 상영 종료", description = "status를 '상영종료'로 바꾸는 api")
public class MovieCloseController {

    private final MovieCloseService movieCloseService;

    @PatchMapping("/{movieId}/status/end")
    public ResponseEntity<String> markMovieAsEnded(@PathVariable Long movieId) {
        movieCloseService.markAsEnded(movieId);
        return ResponseEntity.ok("영화가 상영종료 처리되었습니다.");
    }
}
