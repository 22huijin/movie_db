package com.example.demo.coupon.service;

import com.example.demo.coupon.domain.CouponUser;
import com.example.demo.coupon.dto.UserCouponDTO;
import com.example.demo.coupon.repository.CouponUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponViewerService {
    private final CouponUserRepository couponUserRepository;

    public List<UserCouponDTO> getCouponsByUserId(Long userId) {
        List<CouponUser> couponUsers = couponUserRepository.findByUserUserId(userId);

        return couponUsers.stream()
                .map(cu -> new UserCouponDTO(
                        cu.getCouponUserId(),                   // ✅ coupon_user_id
                        cu.getCoupon().getCouponName(),
                        cu.getCoupon().getDiscountRate(),
                        cu.getValidUntil()
                ))
                .collect(Collectors.toList());
    }

}

