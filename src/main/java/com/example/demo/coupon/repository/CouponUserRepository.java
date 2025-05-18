package com.example.demo.coupon.repository;

import com.example.demo.coupon.domain.CouponUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponUserRepository extends JpaRepository<CouponUser, Long> {
    List<CouponUser> findByUserUserId(Long userId);
}