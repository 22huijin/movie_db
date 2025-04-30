package com.example.demo.movie.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "영화 등록 요청 DTO")
public class MovieCreateRequestDTO {

    @Schema(description = "영화 제목", example = "듄: 파트2")
    private String title;

    @Schema(description = "썸네일 이미지 URL", example = "https://example.com/images/dune2.jpg")
    private String thumbnailUrl;

    @Schema(description = "상영 시간(분)", example = "155")
    private int runningTime;

    @Schema(description = "개봉일", example = "2025-04-17")
    private LocalDate releaseDate;

    @Schema(description = "영화 연령등급", example = "15세 이상")
    private String ageRating;

    @Schema(description = "영화 설명", example = "SF 액션 영화, 인간과 외계의 전투.")
    private String description;

    @Schema(description = "장르", example = "SF, 액션")
    private String genre;

    @Schema(description = "감독", example = "드니 빌뇌브")
    private String director;

    @Schema(description = "출연 배우", example = "티모시 샬라메, 젠다야")
    private String actors;

    @Schema(description = "예고편 URL", example = "https://example.com/trailers/dune2.mp4")
    private String trailerUrl;

    @Schema(description = "스틸컷 이미지 URL", example = "https://example.com/stillcuts/dune2.jpg")
    private String stillCutUrl;
}
