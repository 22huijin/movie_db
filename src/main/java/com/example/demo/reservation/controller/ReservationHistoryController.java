package com.example.demo.reservation.controller;

import com.example.demo.reservation.dto.ReservationHistoryResponseDTO;
import com.example.demo.reservation.service.ReservationHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "예매", description = "예매 및 결제 관련 API")
@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationHistoryController {

  private final ReservationHistoryService reservationService;

  @Operation(summary = "예매 내역 조회", description = "해당 유저의 예매 내역을 반환합니다.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "예매 내역 조회 성공"),
      @ApiResponse(responseCode = "404", description = "해당 유저의 예매 내역 없음")
  })
  @GetMapping("/history/{userId}")
  public List<ReservationHistoryResponseDTO> getReservationHistory(
      @Parameter(name = "userId", description = "유저 ID", example = "1")
      @PathVariable(name = "userId") Long userId
  ) {
    return reservationService.getReservationHistory(userId);
  }
}