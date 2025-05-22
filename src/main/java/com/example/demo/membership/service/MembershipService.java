package com.example.demo.membership.service;

import com.example.demo.membership.repository.MembershipTypeRepository;
import com.example.demo.payment.repository.PaymentRepository;
import com.example.demo.membership.domain.MembershipType;
import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.YearMonth;

@RequiredArgsConstructor
@Service
public class MembershipService {

    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final MembershipTypeRepository membershipTypeRepository;

    @Transactional
    public void updateMembership(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startDate = now.minusMonths(1);

        Long total = paymentRepository.findUserConfirmedTotalBetween(
                userId,
                "CONFIRMED",
                "CONFIRMED",
                startDate,
                now
        );
        if (total == null) total = 0L;

        String targetTypeName;
        if (total >= 200_000L) {
            targetTypeName = "VIP";
        } else if (total >= 100_000L) {
            targetTypeName = "FRIENDS";
        } else {
            targetTypeName = "WELCOME";
        }

        MembershipType newType = membershipTypeRepository.findByMembershipName(targetTypeName)
                .orElseThrow(() -> new IllegalStateException(targetTypeName + " 멤버십 타입이 없습니다."));

        if (!user.getMembershipType().equals(newType)) {
            user.setMembershipType(newType);
        }
    }
}

