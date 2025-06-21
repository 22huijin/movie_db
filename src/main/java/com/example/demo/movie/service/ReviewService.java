package com.example.demo.movie.service;

import com.example.demo.movie.domain.Movie;
import com.example.demo.movie.domain.Review;
import com.example.demo.movie.dto.ReviewRequestDTO;
import com.example.demo.movie.dto.ReviewResponseDTO;
import com.example.demo.movie.repository.MovieRepository;
import com.example.demo.movie.repository.ReviewRepository;
import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    @Transactional
    public Review createReview(ReviewRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Movie movie = movieRepository.findById(dto.getMovieId())
                .orElseThrow(() -> new IllegalArgumentException("Movie not found"));

        Review review = new Review();
        review.setUser(user);
        review.setMovie(movie);
        review.setRating(dto.getRating());
        review.setContext(dto.getContext());
        reviewRepository.save(review);

        // 리뷰 등록 후 평점 평균 계산
        List<Review> reviews = reviewRepository.findByMovie_MovieId(movie.getMovieId());
        float avgRating = (float) reviews.stream()
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0.0);

        movie.setLikeRating(avgRating);
        movieRepository.save(movie); // 평점 업데이트 반영

        return review;
    }

    public List<ReviewResponseDTO> getReviewsByUserId(Long userId) {
        List<Review> reviews = reviewRepository.findByUserUserId(userId);
        return reviews.stream()
                .map(review -> new ReviewResponseDTO(review.getMovie().getMovieId(), review.getRating(), review.getContext()))
                .collect(Collectors.toList());
    }

    public List<ReviewResponseDTO> getReviewsByMovieId(Long movieId) {
        List<Review> reviews = reviewRepository.findByMovie_MovieId(movieId);
        return reviews.stream()
                .map(review -> new ReviewResponseDTO(
                        review.getMovie().getMovieId(),
                        review.getRating(),
                        review.getContext()))
                .collect(Collectors.toList());
    }
}
