package com.example.demo.movie.service;

import com.example.demo.movie.domain.Movie;
import com.example.demo.movie.domain.MovieDetail;
import com.example.demo.movie.dto.MovieCreateRequestDTO;
import com.example.demo.movie.repository.MovieRepository;
import com.example.demo.movie.repository.MovieDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MovieCreateService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieDetailRepository movieDetailRepository;

    public Movie createMovie(MovieCreateRequestDTO movieCreateRequestDTO) {
        // 영화 기본 정보 (movie 테이블에 저장)
        Movie movie = new Movie();
        movie.setTitle(movieCreateRequestDTO.getTitle());
        movie.setThumbnailUrl(movieCreateRequestDTO.getThumbnailUrl());
        movie.setRunningTime(movieCreateRequestDTO.getRunningTime());
        movie.setReleaseDate(movieCreateRequestDTO.getReleaseDate());
        movie.setAgeRating(movieCreateRequestDTO.getAgeRating());
        movie.setDescription(movieCreateRequestDTO.getDescription());

        // '상영중' 또는 '상영예정' 결정
        LocalDate today = LocalDate.now();
        if (movieCreateRequestDTO.getReleaseDate().isBefore(today) || movieCreateRequestDTO.getReleaseDate().isEqual(today)) {
            movie.setStatus("상영중");
        } else {
            movie.setStatus("상영예정");
        }

        // likeRating, totalAudience는 기본값 0으로 설정
        movie.setLikeRating(0);
        movie.setTotalAudience(0);

        // 영화 기본 정보 저장
        movie = movieRepository.save(movie);

        // 영화 세부 정보 (movie_detail 테이블에 저장)
        MovieDetail movieDetail = new MovieDetail();
        movieDetail.setMovie(movie);
        movieDetail.setGenre(movieCreateRequestDTO.getGenre());
        movieDetail.setDirector(movieCreateRequestDTO.getDirector());
        movieDetail.setActors(movieCreateRequestDTO.getActors());
        movieDetail.setTrailerUrl(movieCreateRequestDTO.getTrailerUrl());
        movieDetail.setStillCutUrl(movieCreateRequestDTO.getStillCutUrl());

        // 영화 세부 정보 저장
        movieDetailRepository.save(movieDetail);

        return movie;
    }
}



