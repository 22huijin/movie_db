package com.example.demo.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequestDTO {
    private String email;
    private String nickname;
    private String password;
    private String birthDate; // yyyy-MM-dd 형식
}