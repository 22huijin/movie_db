package com.example.demo.coupon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserCouponDTO {
    private Long couponUserId;
    private String couponName;
    private BigDecimal discountRate;
    private LocalDate validUntil;
}
