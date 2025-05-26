package com.example.demo.payment.controller;

import com.example.demo.payment.dto.PaymentCancelRequestDto;
import com.example.demo.payment.dto.PaymentCancelResponseDto;
import com.example.demo.payment.service.PaymentCancelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Tag(name = "결제 취소 API", description = "결제 취소 처리")
public class PaymentCancelController {

  private final PaymentCancelService paymentCancelService;

  @Operation(
      summary = "결제 취소 요청",
      description = "요청받은 예약 및 결제 내역을 취소하고, 사용된 쿠폰이 있다면 복구합니다.",
      requestBody = @RequestBody(
          required = true,
          description = "취소할 예약의 reservationId",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = PaymentCancelRequestDto.class),
              examples = @ExampleObject(value = """
          {
            "reservationId": 123
          }
        """)
          )
      ),
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "환불 성공 시 반환 메시지",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = PaymentCancelResponseDto.class),
                  examples = @ExampleObject(value = """
            {
              "success": true,
              "message": "12000원 환불되었습니다."
            }
          """)
              )
          ),
          @ApiResponse(responseCode = "400", description = "유효하지 않은 reservationId 또는 결제 정보 없음"),
          @ApiResponse(responseCode = "500", description = "서버 내부 오류")
      }
  )
  @PostMapping("/cancel")
  public ResponseEntity<PaymentCancelResponseDto> cancelPayment(@RequestBody PaymentCancelRequestDto request) {
    PaymentCancelResponseDto response = paymentCancelService.cancelPayment(request);
    return ResponseEntity.ok(response);
  }
}