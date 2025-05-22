package com.example.demo.coupon.scheduler;

import com.example.demo.coupon.domain.Coupon;
import com.example.demo.coupon.domain.CouponUser;
import com.example.demo.coupon.repository.CouponRepository;
import com.example.demo.coupon.repository.CouponUserRepository;
import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponScheduler {

    private final UserRepository userRepository;
    private final CouponRepository couponRepository;
    private final CouponUserRepository couponUserRepository;

    @Scheduled(cron = "0 0 0 * * ?")  // 매일 자정 실행
    @Transactional
    public void issueScheduledCoupons() {
        LocalDate today = LocalDate.now();

        // 오늘 가입한 회원 중 FRIENDS 또는 VIP에게 쿠폰 발급 (couponId = 2)
        List<User> joinTodayUsers = userRepository.findByJoinDateWithMembership(today);
        for (User user : joinTodayUsers) {
            String membership = user.getMembershipType().getMembershipName();
            if ("FRIENDS".equals(membership) || "VIP".equals(membership)) {
                issueCoupon(user, 2L);
            }
        }

        // 오늘 생일인 VIP 회원에게 쿠폰 발급 (couponId = 3)
        List<User> birthdayUsers = userRepository.findByBirthDateWithMembership(today);
        for (User user : birthdayUsers) {
            if ("VIP".equals(user.getMembershipType().getMembershipName())) {
                issueCoupon(user, 3L);
            }
        }
    }

    private void issueCoupon(User user, Long couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new IllegalArgumentException("쿠폰이 없습니다. id: " + couponId));

        CouponUser couponUser = new CouponUser();
        couponUser.setUser(user);
        couponUser.setCoupon(coupon);
        couponUserRepository.save(couponUser);
    }
}
