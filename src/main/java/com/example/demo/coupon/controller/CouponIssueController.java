package com.example.demo.coupon.controller;

import com.example.demo.coupon.service.CouponIssueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coupons")
@RequiredArgsConstructor
@Tag(name = "쿠폰", description = "쿠폰 발행, 쿠폰 조회 API")
public class CouponIssueController {

    private final CouponIssueService couponIssueService;

    @Operation(summary = "쿠폰 수동 발행", description = "관리자가 couponId를 입력하면 해당 쿠폰을 전 유저 대상으로 발급합니다.")
    @PostMapping("/issue/all")
    public ResponseEntity<String> issueCouponToAll(@RequestParam Long couponId) {
        couponIssueService.issueCouponToAllUsers(couponId);
        return ResponseEntity.ok("쿠폰이 전체 회원에게 발급되었습니다.");
    }
}

