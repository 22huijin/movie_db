package com.example.demo.user.service;

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
    private final PasswordEncoder passwordEncoder; // 비밀번호 인코더 주입

    public UserSignUpResponseDTO signup(UserSignUpRequestDTO dto) {
        User user = new User();
        user.setNickname(dto.getNickname());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword())); // 비밀번호 암호화
        user.setMembershipType("WELCOME"); // 고정
        user.setRole("USER"); // 고정
        user.setBirthDate(dto.getBirthDate());
        user.setJoinDate(LocalDate.now());

        User savedUser = userRepository.save(user);

        return new UserSignUpResponseDTO(
                savedUser.getUserId(),
                savedUser.getNickname(),
                savedUser.getEmail(),
                savedUser.getMembershipType(),
                savedUser.getRole(),
                savedUser.getBirthDate(),
                savedUser.getJoinDate()
        );
    }
}
