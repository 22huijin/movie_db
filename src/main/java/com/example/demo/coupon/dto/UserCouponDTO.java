package com.example.demo.coupon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserCouponDTO {
    private String couponName;
    private String discountRate; // 예: "50%"
    private LocalDate validUntil;
}
