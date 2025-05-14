package com.example.demo.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserSignUpRequestDTO {
    private String nickname;
    private String email;
    private String password;
    private LocalDate birthDate;
}