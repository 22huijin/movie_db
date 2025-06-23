package com.example.demo.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "사용자 정보 수정 요청 DTO")
public class UserUpdateRequestDTO {

    @Schema(description = "새 닉네임", example = "new_nickname")
    private String nickname;

    @Schema(description = "새 비밀번호", example = "newPassword123")
    private String password;
}
