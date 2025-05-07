package com.example.demo.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class MovieSearchResponseDTO {
    private Long movieId;
    private String title;
    private String thumbnailUrl;
    private int runningTime;
    private LocalDate releaseDate;
    private String ageRating;
    private String description;
    private float likeRating;
    private int totalAudience;
    private String status;
}
