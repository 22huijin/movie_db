package com.example.demo.user.dto;

import com.example.demo.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserInfoResponseDTO {
    private Long userId;
    private String nickname;
    private String email;
    private String role;
    private LocalDate birthDate;
    private LocalDate joinDate;
    private Integer availablePoint;
    private String membershipName;

    public UserInfoResponseDTO(User user) {
        this.userId = user.getUserId();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.birthDate = user.getBirthDate();
        this.joinDate = user.getJoinDate();
        this.availablePoint = user.getAvailablePoint();
        this.membershipName = user.getMembershipType() != null ? user.getMembershipType().getMembershipName() : null;
    }
}


