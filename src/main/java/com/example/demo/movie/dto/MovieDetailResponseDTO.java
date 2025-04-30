package com.example.demo.movie.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "영화 상세 정보 응답 DTO")
public class MovieDetailResponseDTO {

    @Schema(description = "영화 ID", example = "1")
    private Long movieId;

    @Schema(description = "제목", example = "듄: 파트2")
    private String title;

    @Schema(description = "장르", example = "SF, 액션")
    private String genre;

    @Schema(description = "감독", example = "드니 빌뇌브")
    private String director;

    @Schema(description = "출연 배우", example = "티모시 샬라메, 젠데이아")
    private String actors;

    @Schema(description = "상영 시간 (분)", example = "155")
    private int runningTime;

    @Schema(description = "개봉일", example = "2025-04-17")
    private String releaseDate;

    @Schema(description = "상영 상태", example = "상영중")
    private String status;

    @Schema(description = "예고편 URL", example = "https://youtube.com/trailer")
    private String trailerUrl;

    @Schema(description = "스틸컷 URL", example = "https://example.com/images/dune2.jpg")
    private String stillCutUrl;
}
