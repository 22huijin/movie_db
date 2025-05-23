package com.example.demo.mypage.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieWishlistRequestDTO {
    private Long userId;
    private Long movieId;
}

