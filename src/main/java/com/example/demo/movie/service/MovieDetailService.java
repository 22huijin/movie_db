package com.example.demo.movie.service;

import com.example.demo.movie.domain.Movie;
import com.example.demo.movie.domain.MovieDetail;
import com.example.demo.movie.dto.MovieDetailResponseDTO;
import com.example.demo.movie.repository.MovieDetailRepository;
import com.example.demo.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieDetailService {

    private final MovieRepository movieRepository;
    private final MovieDetailRepository movieDetailRepository;

    public MovieDetailResponseDTO getMovieDetailById(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("해당 영화가 존재하지 않습니다."));

        MovieDetail detail = movieDetailRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("해당 영화의 상세 정보가 존재하지 않습니다."));

        return new MovieDetailResponseDTO(
                movie.getMovieId(),
                movie.getTitle(),
                detail.getGenre(),
                detail.getDirector(),
                detail.getActors(),
                movie.getRunningTime(),
                movie.getReleaseDate().toString(),
                movie.getStatus(),
                detail.getTrailerUrl(),
                detail.getStillCutUrl()
        );
    }
}

