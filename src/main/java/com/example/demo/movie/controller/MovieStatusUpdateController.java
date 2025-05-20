package com.example.demo.movie.controller;

import com.example.demo.movie.service.MovieStatusUpdateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/movies")
@Tag(name = "영화", description = "영화 등록 및 조회 관련 API")
public class MovieStatusUpdateController {

    private final MovieStatusUpdateService movieStatusUpdateService;

    @Autowired
    public MovieStatusUpdateController(MovieStatusUpdateService movieStatusUpdateService) {
        this.movieStatusUpdateService = movieStatusUpdateService;
    }

    @Operation(summary = "상영상태 갱신(UPCOMING에서 SHOWING으로)", description = "매일 0시에 MOVIE 테이블에서 status가 UPCMOING인 영화들의 개봉일을 조회하여 SHOWING으로 변경합니다.(스케줄러 사용)")
    @PutMapping("/update-status-today")
    public String updateMovieStatusToday() {
        movieStatusUpdateService.updateMovieStatus(LocalDate.now());
        return "오늘 날짜 기준으로 영화 상태 업데이트 완료";
    }
}


