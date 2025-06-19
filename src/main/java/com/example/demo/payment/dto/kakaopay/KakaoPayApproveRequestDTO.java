package com.example.demo.payment.dto.kakaopay;

import com.example.demo.payment.dto.PaymentRequestDTO;
import lombok.*;

import java.util.List;

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public class KakaoPayApproveRequestDTO {

    private String tid;
    private String partnerOrderId;
    private String partnerUserId;
    private String pgToken;

    private List<PaymentRequestDTO.PaymentDetail> detail;
  }
