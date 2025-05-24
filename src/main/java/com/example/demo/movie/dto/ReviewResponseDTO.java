package com.example.demo.movie.dto;

public class ReviewResponseDTO {
    private Long movieId;
    private float rating;

    public ReviewResponseDTO(Long movieId, float rating) {
        this.movieId = movieId;
        this.rating = rating;
    }

    public Long getMovieId() {
        return movieId;
    }

    public float getRating() {
        return rating;
    }
}

