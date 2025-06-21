package com.example.demo.movie.dto;

public class ReviewResponseDTO {
    private Long movieId;
    private float rating;
    private String context;

    public ReviewResponseDTO(Long movieId, float rating, String context) {
        this.movieId = movieId;
        this.rating = rating;
        this.context = context;
    }

    public Long getMovieId() {
        return movieId;
    }

    public float getRating() {
        return rating;
    }

    public String getContext() {
        return context;
    }
}

