package com.example.demo.user.service;

import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordCheckService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public PasswordCheckService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean checkPassword(Long userId, String inputPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자가 없습니다."));
        return passwordEncoder.matches(inputPassword, user.getPassword());
    }
}
