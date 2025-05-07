package com.example.demo.movie.scheduler;

import com.example.demo.movie.service.MovieStatusUpdateService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MovieStatusUpdateScheduler {

    private final MovieStatusUpdateService movieStatusUpdateService;

    public MovieStatusUpdateScheduler(MovieStatusUpdateService movieStatusUpdateService) {
        this.movieStatusUpdateService = movieStatusUpdateService;
    }

    // 매일 0시에 실행 (한국 시간 기준)
    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void updateMovieStatusAtMidnight() {
        movieStatusUpdateService.updateMovieStatus(LocalDate.now());
    }
}
