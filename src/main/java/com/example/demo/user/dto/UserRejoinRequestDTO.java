package com.example.demo.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRejoinRequestDTO {
    private String email;
    private String password;
}
