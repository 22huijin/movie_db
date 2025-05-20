package com.example.demo.movie.service;

import com.example.demo.movie.domain.Movie;
import com.example.demo.movie.domain.MovieDetail;
import com.example.demo.movie.dto.MovieUpdateRequestDTO;
import com.example.demo.movie.repository.MovieRepository;
import com.example.demo.movie.repository.MovieDetailRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MovieUpdateService {

    private final MovieRepository movieRepository;
    private final MovieDetailRepository movieDetailRepository;

    public MovieUpdateService(MovieRepository movieRepository, MovieDetailRepository movieDetailRepository) {
        this.movieRepository = movieRepository;
        this.movieDetailRepository = movieDetailRepository;
    }

    @Transactional
    public void updateMovie(Long movieId, MovieUpdateRequestDTO request) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 영화가 없습니다."));

        movie.setTitle(request.getTitle());
        movie.setThumbnailUrl(request.getThumbnailUrl());
        movie.setRunningTime(request.getRunningTime());
        movie.setReleaseDate(request.getReleaseDate());
        movie.setAgeRating(request.getAgeRating());
        movie.setDescription(request.getDescription());

        // releaseDate에 따라 status 자동 설정
        LocalDate today = LocalDate.now();
        if (request.getReleaseDate().isAfter(today)) {
            movie.setStatus("UPCOMING");
        } else {
            movie.setStatus("SHOWING");
        }

        // MovieDetail도 수정
        MovieDetail detail = movie.getMovieDetail();
        if (detail != null) {
            detail.setGenre(request.getGenre());
            detail.setDirector(request.getDirector());
            detail.setActors(request.getActors());
            detail.setTrailerUrl(request.getTrailerUrl());
            detail.setStillCutUrl(request.getStillCutUrl());
        } else {
            // 필요 시 새로 생성
            MovieDetail newDetail = new MovieDetail();
            newDetail.setMovie(movie);
            newDetail.setGenre(request.getGenre());
            newDetail.setDirector(request.getDirector());
            newDetail.setActors(request.getActors());
            newDetail.setTrailerUrl(request.getTrailerUrl());
            newDetail.setStillCutUrl(request.getStillCutUrl());
            movie.setMovieDetail(newDetail);
        }
    }
}

