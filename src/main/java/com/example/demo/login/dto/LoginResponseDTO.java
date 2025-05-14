package com.example.demo.login.dto;

import com.example.demo.user.domain.User;
import lombok.Getter;

import java.time.LocalDate;

public class LoginResponseDTO {
    @Getter
    private Long userId;
    @Getter
    private String nickname;
    @Getter
    private String role;
    @Getter
    private String membershipType;
    @Getter
    private LocalDate birthDate;
    @Getter
    private LocalDate joinDate;

    public LoginResponseDTO(User user) {
        this.userId = user.getUserId();
        this.nickname = user.getNickname();
        this.role = user.getRole();
        this.membershipType = user.getMembershipType();
        this.birthDate = user.getBirthDate();
        this.joinDate = user.getJoinDate();
    }
}