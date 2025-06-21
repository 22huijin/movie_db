// EmailCheckResponseDto.java
package com.example.demo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmailCheckResponseDTO {
    private boolean available;      // true = 사용 가능
    private boolean withdrawStatus;      // true = 탈퇴 상태
    private String message;
}
