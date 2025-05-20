package com.example.demo.movie.controller;

import com.example.demo.movie.domain.Review;
import com.example.demo.movie.dto.ReviewDTO;
import com.example.demo.movie.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
@Tag(name = "영화", description = "영화 등록 및 조회 관련 API")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "리뷰 등록", description = "userId, movieId, 평점을 입력받아 리뷰를 등록합니다.")
    @PostMapping
    public ResponseEntity<String> createReview(@RequestBody ReviewDTO dto) {
        reviewService.createReview(dto);
        return ResponseEntity.ok("리뷰가 저장되었어요.");
    }
}
