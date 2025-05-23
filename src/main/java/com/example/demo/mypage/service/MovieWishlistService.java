package com.example.demo.mypage.service;

import com.example.demo.movie.domain.Movie;
import com.example.demo.movie.domain.MovieDetail;
import com.example.demo.mypage.domain.MovieWishlist;
import com.example.demo.mypage.dto.MovieWishlistRequestDTO;
import com.example.demo.movie.repository.MovieRepository;
import com.example.demo.mypage.dto.MovieWishlistResponseDTO;
import com.example.demo.mypage.repository.MovieWishlistRepository;
import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieWishlistService {

    private final MovieWishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    @Transactional
    public void addWishlist(MovieWishlistRequestDTO request) {
        if (wishlistRepository.existsByUserUserIdAndMovieMovieId(request.getUserId(), request.getMovieId())) {
            throw new IllegalStateException("이미 찜한 영화입니다.");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new IllegalArgumentException("영화를 찾을 수 없습니다."));

        MovieWishlist wishlist = new MovieWishlist();
        wishlist.setUser(user);
        wishlist.setMovie(movie);
        wishlist.setLikedAt(LocalDateTime.now());

        wishlistRepository.save(wishlist);
    }

    public List<MovieWishlistResponseDTO> getWishlistByUserId(Long userId) {
        List<MovieWishlist> wishlists = wishlistRepository.findAllByUserUserIdOrderByLikedAtDesc(userId);

        return wishlists.stream()
                .map(wish -> {
                    Movie movie = wish.getMovie();
                    return new MovieWishlistResponseDTO(
                            movie.getMovieId(),
                            movie.getTitle(),
                            movie.getThumbnailUrl(),
                            movie.getRunningTime(),
                            movie.getAgeRating(),
                            movie.getReleaseDate()
                    );
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteWishlist(Long userId, Long movieId) {
        if (!wishlistRepository.existsByUserUserIdAndMovieMovieId(userId, movieId)) {
            throw new EntityNotFoundException("찜한 영화가 존재하지 않습니다.");
        }
        wishlistRepository.deleteByUserUserIdAndMovieMovieId(userId, movieId);
    }

    public String checkWishlistStatus(Long userId, Long movieId) {
        boolean exists = wishlistRepository.existsByUserUserIdAndMovieMovieId(userId, movieId);
        return exists ? "yes" : "no";
    }

}

