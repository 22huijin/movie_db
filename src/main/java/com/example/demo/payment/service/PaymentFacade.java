package com.example.demo.payment.service;

import com.example.demo.payment.dto.PaymentRequestDTO;
import com.example.demo.payment.dto.kakaopay.KakaoPayApproveRequestDTO;
import com.example.demo.payment.dto.kakaopay.KakaoPayApproveResponseDTO;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentFacade {

  private final KakaoPayService kakaoPayService;
  private final PaymentService  paymentService;
  @Value("${kakao.pay.cid}") private String cid;

  public KakaoPayApproveResponseDTO approveAndFinalize(
      String tid, String partnerOrderId, String partnerUserId, String pgToken,
      List<PaymentRequestDTO.PaymentDetail> detailList) {

    /* 1) 카카오 승인 */
    KakaoPayApproveRequestDTO kpReq = KakaoPayApproveRequestDTO.builder()
        .tid(tid)
        .partnerOrderId(partnerOrderId)
        .partnerUserId(partnerUserId)
        .pgToken(pgToken)
        .build();

    KakaoPayApproveResponseDTO kpRes = kakaoPayService.approve(kpReq);

    /* 2) 내부 결제 */
    PaymentRequestDTO domainReq = new PaymentRequestDTO();
    domainReq.setPaymentMethod("KAKAO_PAY");
    domainReq.setDetails(detailList);          // 그대로 사용

    paymentService.processPayment(domainReq);
    return kpRes;
  }
}