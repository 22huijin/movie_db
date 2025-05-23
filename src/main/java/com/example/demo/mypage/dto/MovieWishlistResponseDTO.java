package com.example.demo.mypage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class MovieWishlistResponseDTO {
    private Long movieId;
    private String title;
    private String thumbnailUrl;
    private int runningTime;
    private String ageRating;
    private LocalDate releaseDate;
}


