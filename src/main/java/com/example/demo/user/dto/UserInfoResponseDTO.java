package com.example.demo.user.dto;

import com.example.demo.user.domain.User;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserInfoResponseDTO {
    private String nickname;
    private String email;
    private String membershipType;
    private LocalDate birthDate;
    private LocalDate joinDate;

    public UserInfoResponseDTO(User user) {
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.membershipType = user.getMembershipType() != null
                ? user.getMembershipType().getMembershipName()
                : null;
        this.birthDate = user.getBirthDate();
        this.joinDate = user.getJoinDate();
    }
}
