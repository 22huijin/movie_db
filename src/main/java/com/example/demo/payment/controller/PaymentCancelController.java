package com.example.demo.payment.controller;

import com.example.demo.payment.dto.PaymentCancelRequestDto;
import com.example.demo.payment.dto.PaymentCancelResponseDto;
import com.example.demo.payment.service.PaymentCancelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Tag(name = "예매", description = "예매 및 결제 관련 API")
public class PaymentCancelController {

  private final PaymentCancelService paymentCancelService;

  @Operation(summary = "결제 취소 요청", description = "해당 예매의 결제를 취소 처리합니다.")
  @PostMapping("/cancel")
  public ResponseEntity<PaymentCancelResponseDto> cancelPayment(@RequestBody PaymentCancelRequestDto request) {
    PaymentCancelResponseDto response = paymentCancelService.cancelPayment(request);
    return ResponseEntity.ok(response);
  }
}