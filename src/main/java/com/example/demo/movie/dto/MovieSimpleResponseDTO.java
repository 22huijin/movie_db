package com.example.demo.movie.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Schema(description = "상영작/예정작 요약 정보 응답 DTO")
public class MovieSimpleResponseDTO {

    @Schema(description = "영화 ID", example = "1")
    private Long movieId;

    @Schema(description = "영화 제목", example = "듄: 파트2")
    private String title;

    @Schema(description = "썸네일 이미지 URL", example = "https://example.com/images/dune2.jpg")
    private String thumbnailUrl;

    @Schema(description = "상영 시간(분)", example = "155")
    private int runningTime;

    @Schema(description = "개봉일", example = "2025-04-17")
    private LocalDate releaseDate;

    @Schema(description = "상영 상태", example = "상영중") // 또는 "개봉예정"
    private String status;
}
