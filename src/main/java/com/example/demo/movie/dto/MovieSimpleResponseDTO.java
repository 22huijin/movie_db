package com.example.demo.movie.dto;

import com.example.demo.movie.domain.Movie;
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

    @Schema(description = "상영 등급", example = "15세이상관람가")
    private String ageRating;

    @Schema(description = "영화 소개", example = "사구 행성 아라키스를 둘러싼 전쟁이 시작된다.")
    private String description;

    @Schema(description = "좋아요 평점", example = "4.8")
    private double likeRating;

    @Schema(description = "누적 관객 수", example = "1000000")
    private int totalAudience;

    @Schema(description = "상영 상태", example = "SHOWING") // 또는 UPCOMING
    private String status;

    public static MovieSimpleResponseDTO fromEntity(Movie movie) {
        return new MovieSimpleResponseDTO(
                movie.getMovieId(),
                movie.getTitle(),
                movie.getThumbnailUrl(),
                movie.getRunningTime(),
                movie.getReleaseDate(),
                movie.getAgeRating(),
                movie.getDescription(),
                movie.getLikeRating(),
                movie.getTotalAudience(),
                movie.getStatus()
        );
    }
}
