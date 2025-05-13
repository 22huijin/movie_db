package com.example.demo.user.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserInfoResponseDTO {
    private String nickname;
    private String email;
    private String membershipType;
    private LocalDate birthDate;
    private LocalDate joinDate;
}

