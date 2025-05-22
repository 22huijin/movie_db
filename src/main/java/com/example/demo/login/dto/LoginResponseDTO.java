package com.example.demo.login.dto;

import com.example.demo.user.domain.User;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class LoginResponseDTO {
    private Long userId;
    private String nickname;
    private String role;
    private String membershipType;
    private LocalDate birthDate;
    private LocalDate joinDate;

    public LoginResponseDTO(User user) {
        this.userId = user.getUserId();
        this.nickname = user.getNickname();
        this.role = user.getRole();
        this.membershipType = user.getMembershipType() != null
                ? user.getMembershipType().getMembershipName()
                : null;
        this.birthDate = user.getBirthDate();
        this.joinDate = user.getJoinDate();
    }
}
