package com.example.demo.coupon.repository;

import com.example.demo.coupon.domain.CouponUser;
import com.example.demo.coupon.domain.CouponUserId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponUserRepository extends JpaRepository<CouponUser, CouponUserId> {
}