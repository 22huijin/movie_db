package com.example.demo.coupon.dto;

import lombok.Getter;

@Getter
public class CouponRequestDTO {
    private Long couponId; // 현재는 PathVariable로 받지만, 필요시 Body로 받을 수도 있음
}
