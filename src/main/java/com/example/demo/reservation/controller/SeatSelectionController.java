package com.example.demo.reservation.controller;

import com.example.demo.reservation.dto.SeatSelectionRequestDTO;
import com.example.demo.reservation.dto.SeatSelectionResponseDTO;
import com.example.demo.reservation.dto.LockPriceDTO;
import com.example.demo.reservation.service.SeatReservationService;
import com.example.demo.reservation.service.SeatSelectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "좌석 선택 API", description = "좌석을 예약합니다.")
@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class SeatSelectionController {

  private final SeatSelectionService seatSelectionService;
  private final SeatReservationService seatReservationService;

  @Operation(summary = "좌석 선택 및 잠금", description = "좌석을 선택하여 10분간 잠금 상태로 설정합니다.")
  @PostMapping("/select-seats")
  public SeatSelectionResponseDTO selectSeats(@RequestBody SeatSelectionRequestDTO dto) {
    try {
      // 1) 좌석 잠금 + availableSeats 감소 (트랜잭션)
      List<Long> lockIds = seatSelectionService.selectLockIds(dto);

      // 2) 요금 계산 (비트랜잭션)
      List<LockPriceDTO> result = seatReservationService.calculatePrices(
          dto.getScheduleId(), dto.getSeats(), lockIds
      );

      // 3) 최종 응답
      return new SeatSelectionResponseDTO(
          true,
          "10분 이내에 결제하셔야 예약이 완료됩니다.",
          result
      );

    } catch (IllegalArgumentException | IllegalStateException ex) {
      // 비즈니스 예외 처리
      return new SeatSelectionResponseDTO(false, ex.getMessage(), List.of());
    }
  }
}