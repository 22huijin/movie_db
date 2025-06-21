package com.example.demo.user.service;

import com.example.demo.coupon.domain.Coupon;
import com.example.demo.coupon.domain.CouponUser;
import com.example.demo.coupon.repository.CouponRepository;
import com.example.demo.coupon.repository.CouponUserRepository;
import com.example.demo.membership.domain.MembershipType;
import com.example.demo.membership.repository.MembershipTypeRepository;
import com.example.demo.user.domain.User;
import com.example.demo.user.dto.UserSignUpRequestDTO;
import com.example.demo.user.dto.UserSignUpResponseDTO;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserSignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MembershipTypeRepository membershipTypeRepository;
    private final CouponRepository couponRepository;
    private final CouponUserRepository couponUserRepository;

    public UserSignUpResponseDTO signup(UserSignUpRequestDTO dto) {
        MembershipType welcomeMembership = membershipTypeRepository.findByMembershipName("WELCOME")
                .orElseThrow(() -> new IllegalStateException("WELCOME 멤버십 타입이 존재하지 않습니다."));

        User user = new User();
        user.setNickname(dto.getNickname());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setMembershipType(welcomeMembership);
        user.setRole("USER");
        user.setBirthDate(dto.getBirthDate());
        user.setJoinDate(LocalDate.now());
        user.setAvailablePoint(0);

        User savedUser = userRepository.save(user);

        Coupon welcomeCoupon = couponRepository.findById(1L)
                .orElseThrow(() -> new IllegalStateException("쿠폰 ID 1번이 존재하지 않습니다."));

        CouponUser couponUser = new CouponUser();
        couponUser.setUser(savedUser);
        couponUser.setCoupon(welcomeCoupon);
        couponUser.setUsageStatus("unused");
        couponUser.setValidUntil(LocalDate.now().plusMonths(1)); // 유효기간 1달

        couponUserRepository.save(couponUser);

        return new UserSignUpResponseDTO(
                savedUser.getUserId(),
                savedUser.getNickname(),
                savedUser.getEmail(),
                savedUser.getMembershipType().getMembershipName(),
                savedUser.getRole(),
                savedUser.getBirthDate(),
                savedUser.getJoinDate(),
                savedUser.getAvailablePoint()
        );
    }
}

