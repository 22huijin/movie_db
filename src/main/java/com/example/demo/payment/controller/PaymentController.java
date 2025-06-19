package com.example.demo.payment.controller;

import com.example.demo.payment.dto.PaymentRequestDTO;
import com.example.demo.payment.dto.PaymentResponseDTO;
import com.example.demo.payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Deprecated
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Tag(name = "예매", description = "예매 및 결제 관련 API")
public class PaymentController {

  private final PaymentService paymentService;

  @Operation(
      summary = "결제 요청",
      description = "lockId와 paymentMethod, (선택적으로 couponUserId)를 입력하여 결제를 요청합니다.",
      requestBody = @RequestBody(
          required = true,
          content = @Content(
              schema = @Schema(implementation = PaymentRequestDTO.class)
          )
      ),
      responses = {
          @ApiResponse(responseCode = "200", description = "결제 성공", content = @Content(schema = @Schema(implementation = PaymentResponseDTO.class))),
          @ApiResponse(responseCode = "400", description = "잘못된 요청"),
          @ApiResponse(responseCode = "500", description = "서버 오류")
      }
  )
  @PostMapping
  public PaymentResponseDTO requestPayment(@org.springframework.web.bind.annotation.RequestBody PaymentRequestDTO request) {
    return paymentService.processPayment(request);
  }
}