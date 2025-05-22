package com.example.demo.user.service;

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

    public UserSignUpResponseDTO signup(UserSignUpRequestDTO dto) {
        MembershipType welcomeMembership = membershipTypeRepository.findByMembershipName("WELCOME")
                .orElseThrow(() -> new IllegalStateException("WELCOME 멤버십 타입이 존재하지 않습니다."));

        User user = new User();
        user.setNickname(dto.getNickname());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setMembershipType(welcomeMembership);  // 문자열 X, 객체 O
        user.setRole("USER");
        user.setBirthDate(dto.getBirthDate());
        user.setJoinDate(LocalDate.now());

        User savedUser = userRepository.save(user);

        return new UserSignUpResponseDTO(
                savedUser.getUserId(),
                savedUser.getNickname(),
                savedUser.getEmail(),
                savedUser.getMembershipType().getMembershipName(), // 이름 추출
                savedUser.getRole(),
                savedUser.getBirthDate(),
                savedUser.getJoinDate()
        );
    }
}
