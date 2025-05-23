package com.example.demo.mypage.repository;

import com.example.demo.mypage.domain.MovieWishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import java.util.List;

public interface MovieWishlistRepository extends JpaRepository<MovieWishlist, Long> {
    boolean existsByUserUserIdAndMovieMovieId(Long userId, Long movieId);
    List<MovieWishlist> findAllByUserUserIdOrderByLikedAtDesc(Long userId);
    void deleteByUserUserIdAndMovieMovieId(Long userId, Long movieId);
}

