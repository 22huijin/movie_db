package com.example.demo.movie.controller;

import com.example.demo.movie.service.MovieStatusUpdateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/movies")
@Tag(name = "상영상태 갱신", description = "오늘 날짜와 개봉일 비교해 상영상태 갱신하는 API")
public class MovieStatusUpdateController {

    private final MovieStatusUpdateService movieStatusUpdateService;

    @Autowired
    public MovieStatusUpdateController(MovieStatusUpdateService movieStatusUpdateService) {
        this.movieStatusUpdateService = movieStatusUpdateService;
    }

    @PutMapping("/update-status-today")
    public String updateMovieStatusToday() {
        movieStatusUpdateService.updateMovieStatus(LocalDate.now());
        return "오늘 날짜 기준으로 영화 상태 업데이트 완료";
    }
}


