package com.example.demo.mypage.controller;

import com.example.demo.mypage.dto.MovieWishlistRequestDTO;
import com.example.demo.mypage.dto.MovieWishlistResponseDTO;
import com.example.demo.mypage.service.MovieWishlistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishlist")
@Tag(name = "마이페이지", description = "회원정보 조회/영화 찜/내 리뷰 모아보기 API")
public class MovieWishlistController {

    private final MovieWishlistService wishlistService;

    @Operation(summary = "영화 찜", description = "user_id와 movie_id를 입력받아 movie_wishlist 테이블에 추가합니다.")
    @PostMapping
    public ResponseEntity<String> addWishlist(@RequestBody MovieWishlistRequestDTO request) {
        wishlistService.addWishlist(request);
        return ResponseEntity.ok("찜 목록에 추가되었습니다.");
    }

    @Operation(summary = "찜한 영화 모아보기", description = "user_id 입력받아 회원이 찜한 영화를 리스트 형태로 반환합니다.")
    @GetMapping("/{userId}")
    public ResponseEntity<List<MovieWishlistResponseDTO>> getWishlist(@PathVariable Long userId) {
        List<MovieWishlistResponseDTO> wishlist = wishlistService.getWishlistByUserId(userId);
        return ResponseEntity.ok(wishlist);
    }

    @Operation(summary = "찜 취소", description = "movie_wishlist 테이블에서 데이터를 삭제합니다.")
    @DeleteMapping("/cancel")
    public ResponseEntity<String> deleteWishlist(
            @RequestParam Long userId,
            @RequestParam Long movieId) {

        wishlistService.deleteWishlist(userId, movieId);
        return ResponseEntity.ok("찜이 성공적으로 삭제되었습니다.");
    }

    @Operation(summary = "찜 여부 확인", description = "user_id와 movie_id를 받아 회원이 해당 영화를 찜했는지 아닌지를 반환합니다.")
    @GetMapping("/wishlist/check")
    public ResponseEntity<String> checkWishlist(
            @RequestParam Long userId,
            @RequestParam Long movieId) {

        String result = wishlistService.checkWishlistStatus(userId, movieId);
        return ResponseEntity.ok(result);
    }


}

