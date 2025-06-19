package com.example.demo.payment.dto.kakaopay;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class KakaoPayApproveResponseDTO {
  private String aid;
  private String tid;
  private String cid;
  @JsonProperty("partner_order_id")
  private String partnerOrderId;
  @JsonProperty("partner_user_id")
  private String partnerUserId;
  @JsonProperty("payment_method_type")
  private String paymentMethodType;
}