package com.example.demo.movie.dto;

import lombok.Getter;
import java.time.LocalDate;

@Getter
public class MovieUpdateRequestDTO {
    private String title;
    private String thumbnailUrl;
    private int runningTime;
    private LocalDate releaseDate;
    private String ageRating;
    private String description;

    private String genre;
    private String director;
    private String actors;
    private String trailerUrl;
    private String stillCutUrl;
}

