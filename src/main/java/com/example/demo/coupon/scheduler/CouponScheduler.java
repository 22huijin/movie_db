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

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponScheduler {

    private final UserRepository userRepository;
    private final CouponRepository couponRepository;
    private final CouponUserRepository couponUserRepository;

    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정
    public void issueScheduledCoupons() {
        LocalDate today = LocalDate.now();

        // 가입일이 오늘인 FRIENDS, VIP
        List<User> joinTodayUsers = userRepository.findByJoinDate(today);
        for (User user : joinTodayUsers) {
            if (user.getMembershipType().equals("FRIENDS") || user.getMembershipType().equals("VIP")) {
                issueCoupon(user, 2L); // 반값 쿠폰
            }
        }

        // 생일이 오늘인 VIP
        List<User> birthdayUsers = userRepository.findByBirthDate(today);
        for (User user : birthdayUsers) {
            if (user.getMembershipType().equals("VIP")) {
                issueCoupon(user, 3L); // 전액 쿠폰
            }
        }
    }

    private void issueCoupon(User user, Long couponId) {
        Coupon coupon = couponRepository.findById(couponId).orElseThrow();
        CouponUser couponUser = new CouponUser();
        couponUser.setUser(user);
        couponUser.setCoupon(coupon);
        couponUser.setStatus("unused");
        couponUser.setValidUntil(LocalDate.now().plusMonths(1));
        couponUserRepository.save(couponUser);
    }
}
