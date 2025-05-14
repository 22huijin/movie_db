package com.example.demo.movie.controller;

import com.example.demo.movie.dto.MovieUpdateRequestDTO;
import com.example.demo.movie.service.MovieUpdateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
@Tag(name = "영화 정보 수정", description = "영화 정보 수정 api")
public class MovieUpdateController {

    private final MovieUpdateService movieUpdateService;

    public MovieUpdateController(MovieUpdateService movieService) {
        this.movieUpdateService = movieService;
    }

    @PutMapping("/{movieId}/update")
    public ResponseEntity<String> updateMovie(
            @PathVariable Long movieId,
            @RequestBody MovieUpdateRequestDTO request) {

        movieUpdateService.updateMovie(movieId, request);
        return ResponseEntity.ok("영화 정보가 수정되었습니다");
    }
}

