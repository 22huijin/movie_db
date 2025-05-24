package com.example.demo.movie.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "리뷰 생성 요청 DTO")
public class ReviewRequestDTO {

    @Schema(description = "리뷰를 작성한 사용자 ID", example = "1")
    private Long userId;

    @Schema(description = "리뷰 대상 영화 ID", example = "2")
    private Long movieId;

    @Schema(description = "평점 (0.0 ~ 5.0)", example = "4.5")
    private float rating;
}

