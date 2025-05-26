package com.example.demo.payment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentCancelRequestDto {
  private Long reservationId;
}