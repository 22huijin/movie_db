package com.example.demo.coupon.controller;

import com.example.demo.coupon.dto.UserCouponDTO;
import com.example.demo.coupon.service.CouponViewerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "쿠폰", description = "쿠폰 발행, 쿠폰 조회 API")
public class CouponViewerController {

    @Autowired
    private CouponViewerService couponViewerService;

    @Operation(summary = "쿠폰함 조회", description = "해당 유저가 가진 사용 가능한 쿠폰 정보를 반환합니다.")
    @GetMapping("/{userId}/coupons")
    public List<UserCouponDTO> getUserCoupons(@PathVariable Long userId) {
        return couponViewerService.getCouponsByUserId(userId);
    }
}


