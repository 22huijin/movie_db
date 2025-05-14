package com.example.demo.user.service;

import com.example.demo.user.domain.User;
import com.example.demo.user.dto.UserInfoResponseDTO;
import com.example.demo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService {

    @Autowired
    private UserRepository userRepository;

    public UserInfoResponseDTO getUserInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        UserInfoResponseDTO dto = new UserInfoResponseDTO();
        dto.setNickname(user.getNickname());
        dto.setEmail(user.getEmail());
        dto.setMembershipType(user.getMembershipType());
        dto.setBirthDate(user.getBirthDate());
        dto.setJoinDate(user.getJoinDate());

        return dto;
    }
}
