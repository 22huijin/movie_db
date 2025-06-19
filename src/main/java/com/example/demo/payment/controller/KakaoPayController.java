package com.example.demo.payment.controller;

import com.example.demo.payment.dto.kakaopay.KakaoPayApproveRequestDTO;
import com.example.demo.payment.dto.kakaopay.KakaoPayReadyRequestDTO;
import com.example.demo.payment.dto.kakaopay.KakaoPayReadyResponseDTO;
import com.example.demo.payment.dto.kakaopay.KakaoPayApproveResponseDTO;
import com.example.demo.payment.service.KakaoPayService;
import com.example.demo.payment.service.PaymentFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "예매", description = "예매 및 결제 관련 API")
@RestController
@RequestMapping("/api/payments/kakao")
@RequiredArgsConstructor
public class KakaoPayController {

  private final KakaoPayService kakaoPayService;
  private final PaymentFacade   paymentFacade;

  /* 1. ready */
  @PostMapping("/ready")
  @Operation(summary = "카카오페이 결제 준비(ready)")
  public KakaoPayReadyResponseDTO kakaoPayReady(@RequestBody KakaoPayReadyRequestDTO req) {
    return kakaoPayService.ready(req);
  }

  /* 2. approve */
  @PostMapping("/approve")
  @Operation(summary = "카카오페이 결제 승인(approve)")
  public KakaoPayApproveResponseDTO kakaoPayApprove(
      @RequestBody KakaoPayApproveRequestDTO req) {

    return paymentFacade.approveAndFinalize(
        req.getTid(),
        req.getPartnerOrderId(),
        req.getPartnerUserId(),
        req.getPgToken(),
        req.getDetail()
    );
  }
}