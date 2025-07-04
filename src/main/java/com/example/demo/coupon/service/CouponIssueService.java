package com.example.demo.coupon.service;

import com.example.demo.coupon.domain.Coupon;
import com.example.demo.coupon.domain.CouponUser;
import com.example.demo.coupon.repository.CouponRepository;
import com.example.demo.coupon.repository.CouponUserRepository;
import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponIssueService {

    private final CouponRepository couponRepository;
    private final CouponUserRepository couponUserRepository;
    private final UserRepository userRepository;

    @Transactional
    public void issueCouponToAllUsers(String couponName) {
        Coupon coupon = couponRepository.findByCouponName(couponName)
                .orElseThrow(() -> new IllegalArgumentException("해당 이름의 쿠폰이 존재하지 않습니다."));

        List<User> users = userRepository.findAll();

        for (User user : users) {
            CouponUser couponUser = new CouponUser();
            couponUser.setUser(user);
            couponUser.setCoupon(coupon);
            couponUser.setUsageStatus("unused");
            couponUser.setValidUntil(LocalDate.now().plusMonths(1));
            couponUserRepository.save(couponUser);
        }
    }

}

