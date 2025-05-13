package com.example.demo.coupon.service;

import com.example.demo.coupon.domain.CouponUser;
import com.example.demo.coupon.dto.UserCouponDTO;
import com.example.demo.coupon.repository.CouponUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CouponViewerService {

    @Autowired
    private CouponUserRepository couponUserRepository;

    public List<UserCouponDTO> getCouponsByUserId(Long userId) {
        List<CouponUser> couponUsers = couponUserRepository.findByUserUserId(userId);

        return couponUsers.stream()
                .filter(cu -> {
                    boolean notUsed = !"used".equalsIgnoreCase(cu.getStatus());
                    boolean notExpired = cu.getValidUntil() == null || cu.getValidUntil().isAfter(LocalDate.now());
                    return notUsed && notExpired;
                })
                .map(cu -> {
                    String name = cu.getCoupon().getCouponName();
                    String discount = cu.getCoupon().getDiscountAmount().multiply(BigDecimal.valueOf(100))
                            .stripTrailingZeros()
                            .toPlainString() + "%";
                    LocalDate validUntil = cu.getValidUntil();
                    return new UserCouponDTO(name, discount, validUntil);
                })
                .collect(Collectors.toList());
    }
}

