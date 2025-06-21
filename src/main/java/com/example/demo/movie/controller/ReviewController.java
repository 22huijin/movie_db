package com.example.demo.movie.controller;

import com.example.demo.movie.domain.Review;
import com.example.demo.movie.dto.ReviewRequestDTO;
import com.example.demo.movie.dto.ReviewResponseDTO;
import com.example.demo.movie.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Tag(name = "영화", description = "영화 등록 및 조회 관련 API")
    @Operation(summary = "리뷰 등록", description = "userId, movieId, 평점을 입력받아 리뷰를 등록합니다.")
    @PostMapping
    public ResponseEntity<String> createReview(@RequestBody ReviewRequestDTO dto) {
        reviewService.createReview(dto);
        return ResponseEntity.ok("리뷰가 저장되었어요.");
    }

    @Tag(name = "마이페이지", description = "회원정보 조회/영화 찜/내 리뷰 모아보기 API")
    @GetMapping("/user/{userId}")
    @Operation(summary = "내 리뷰 모아보기", description = "userId 기준으로 리뷰를 조회합니다.")
    public List<ReviewResponseDTO> getReviewsByUser(@PathVariable Long userId) {
        return reviewService.getReviewsByUserId(userId);
    }

    @Tag(name = "영화", description = "영화 등록 및 조회 관련 API")
    @GetMapping("/movie/{movieId}")
    @Operation(summary = "영화별 리뷰 모아보기", description = "movieId 기준으로 리뷰를 조회합니다.")
    public List<ReviewResponseDTO> getReviewsByMovieId(@PathVariable Long movieId) {
        return reviewService.getReviewsByMovieId(movieId);
    }
}
