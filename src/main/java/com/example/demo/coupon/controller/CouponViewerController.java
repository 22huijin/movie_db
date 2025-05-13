package com.example.demo.coupon.controller;

import com.example.demo.coupon.dto.UserCouponDTO;
import com.example.demo.coupon.service.CouponViewerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "쿠폰함 조회", description = "쿠폰함 조회 api")
public class CouponViewerController {

    @Autowired
    private CouponViewerService couponViewerService;

    @GetMapping("/{userId}/coupons")
    public List<UserCouponDTO> getUserCoupons(@PathVariable Long userId) {
        return couponViewerService.getCouponsByUserId(userId);
    }
}


