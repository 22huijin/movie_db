package com.example.demo.payment.controller;

import com.example.demo.payment.dto.PaymentCancelRequestDto;
import com.example.demo.payment.dto.PaymentCancelResponseDto;
import com.example.demo.payment.service.PaymentCancelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Tag(name = "결제 API", description = "환불 요청 처리")
public class PaymentCancelController {

  private final PaymentCancelService paymentCancelService;

  @PostMapping("/cancel")
  public ResponseEntity<PaymentCancelResponseDto> cancelPayment(@RequestBody PaymentCancelRequestDto request) {
    PaymentCancelResponseDto response = paymentCancelService.cancelPayment(request);
    return ResponseEntity.ok(response);
  }
}