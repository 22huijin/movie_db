package com.example.demo.user.service;

import com.example.demo.user.domain.User;
import com.example.demo.user.dto.EmailCheckResponseDTO;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailDuplicateCheckService {

    private final UserRepository userRepository;

    public EmailCheckResponseDTO checkEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            return new EmailCheckResponseDTO(true, false, "사용 가능한 이메일입니다.");
        }

        User user = userOpt.get();
        if ("withdraw".equals(user.getWithdrawStatus())) {
            return new EmailCheckResponseDTO(false, true, "탈퇴한 계정입니다. 재가입을 진행할 수 있습니다.");
        } else {
            return new EmailCheckResponseDTO(false, false, "이미 가입된 이메일입니다.");
        }
    }
}
