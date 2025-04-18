package com.example.demo.user.repository;

import com.example.demo.user.domain.CouponUser;
import com.example.demo.user.domain.CouponUserId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponUserRepository extends JpaRepository<CouponUser, CouponUserId> {
}