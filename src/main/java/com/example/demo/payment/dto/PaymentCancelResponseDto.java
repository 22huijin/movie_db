package com.example.demo.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentCancelResponseDto {
  private boolean success;
  private String message;
}