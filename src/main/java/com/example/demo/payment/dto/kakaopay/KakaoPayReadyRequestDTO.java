package com.example.demo.payment.dto.kakaopay;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoPayReadyRequestDTO {
  private String partnerOrderId;
  private String partnerUserId;
  private String itemName;
  private Integer quantity;
  private Integer totalAmount;
  private Integer taxFreeAmount;
  private String approvalUrl;
  private String cancelUrl;
  private String failUrl;
}