// 등록 결과 응답 DTO
package com.example.demo.screen.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScreenRegisterResponseDTO {
  private boolean success;
  private String message;
}