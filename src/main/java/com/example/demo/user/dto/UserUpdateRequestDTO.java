// UserUpdateRequestDTO.java
package com.example.demo.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequestDTO {
    private String nickname;
    private String password;     // 새 비밀번호
}
