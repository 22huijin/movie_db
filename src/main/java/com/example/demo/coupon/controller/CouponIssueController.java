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
@Tag(name = "쿠폰 발행", description = "특정 쿠폰을 전 회원에게 수동 발행하는 API")
public class CouponIssueController {

    private final CouponIssueService couponIssueService;

    @PostMapping("/issue/all")
    @Operation(summary = "쿠폰 전체 발행", description = "입력받은 쿠폰 ID를 모든 회원에게 발행합니다.")
    public ResponseEntity<String> issueCouponToAll(@RequestParam Long couponId) {
        couponIssueService.issueCouponToAllUsers(couponId);
        return ResponseEntity.ok("쿠폰이 전체 회원에게 발급되었습니다.");
    }
}

