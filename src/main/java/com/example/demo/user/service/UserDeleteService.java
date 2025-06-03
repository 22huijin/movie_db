package com.example.demo.user.service;

import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDeleteService {

    private final UserRepository userRepository;

    @Transactional
    public void withdrawUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자가 존재하지 않습니다."));

        if (user.getWithdrawStatus().equals("withdraw")) {
            throw new IllegalStateException("이미 탈퇴한 사용자입니다.");
        }

        user.setWithdrawStatus("withdraw");
    }
}
