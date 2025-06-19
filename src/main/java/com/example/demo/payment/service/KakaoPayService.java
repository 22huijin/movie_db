package com.example.demo.payment.service;

import com.example.demo.payment.dto.kakaopay.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class KakaoPayService {

  @Value("${kakao.pay.cid}")       private String cid;
  @Value("${kakao.pay.admin-key}") private String adminKey;

  private final WebClient webClient = WebClient.builder()
      .baseUrl("https://kapi.kakao.com")
      .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
      .build();

  /* 결제 준비 */
  public KakaoPayReadyResponseDTO ready(KakaoPayReadyRequestDTO dto) {
    return webClient.post()
        .uri("/v1/payment/ready")
        .header("Authorization", "KakaoAK " + adminKey)
        .body(BodyInserters.fromFormData("cid", cid)
            .with("partner_order_id", dto.getPartnerOrderId())
            .with("partner_user_id", dto.getPartnerUserId())
            .with("item_name", dto.getItemName())
            .with("quantity", dto.getQuantity().toString())
            .with("total_amount", dto.getTotalAmount().toString())
            .with("tax_free_amount", dto.getTaxFreeAmount().toString())
            .with("approval_url", dto.getApprovalUrl())
            .with("cancel_url", dto.getCancelUrl())
            .with("fail_url", dto.getFailUrl()))
        .retrieve()
        .bodyToMono(KakaoPayReadyResponseDTO.class)
        .block();
  }

  /* 결제 승인 */
  public KakaoPayApproveResponseDTO approve(KakaoPayApproveRequestDTO dto) {
    return webClient.post()
        .uri("/v1/payment/approve")
        .header("Authorization", "KakaoAK " + adminKey)
        .body(BodyInserters.fromFormData("cid", cid)
            .with("tid", dto.getTid())
            .with("partner_order_id", dto.getPartnerOrderId())
            .with("partner_user_id", dto.getPartnerUserId())
            .with("pg_token", dto.getPgToken()))
        .retrieve()
        .bodyToMono(KakaoPayApproveResponseDTO.class)
        .block();
  }
}