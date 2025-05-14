package com.example.demo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UserSignUpResponseDTO {
    private Long userId;
    private String nickname;
    private String email;
    private String membershipType;
    private String role;
    private LocalDate birthDate;
    private LocalDate joinDate;
}