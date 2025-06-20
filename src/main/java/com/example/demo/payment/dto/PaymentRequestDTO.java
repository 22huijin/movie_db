package com.example.demo.payment.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaymentRequestDTO {
  private String paymentMethod;
  private Integer usePoint;

  private List<PaymentDetail> details;

  @Getter
  @Setter
  public static class PaymentDetail {
    private Long lockId;
    private Long couponUserId; // optional
  }
}