package com.example.demo.payment.dto.kakaopay;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class KakaoPayReadyResponseDTO {
  private String tid;
  @JsonProperty("next_redirect_pc_url")
  private String nextRedirectPcUrl;
  @JsonProperty("created_at")
  private String createdAt;
}