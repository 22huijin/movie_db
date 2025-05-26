package com.example.demo.reservation.controller;

import com.example.demo.reservation.dto.SeatSelectionRequestDTO;
import com.example.demo.reservation.dto.SeatSelectionResponseDTO;
import com.example.demo.reservation.dto.LockPriceDTO;
import com.example.demo.reservation.service.SeatReservationService;
import com.example.demo.reservation.service.SeatSelectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "예매", description = "예매 및 결제 관련 API")
@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class SeatSelectionController {

  private final SeatSelectionService seatSelectionService;
  private final SeatReservationService seatReservationService;

  @Operation(
      summary = "좌석 선택 및 잠금",
      description = "좌석을 선택하여 10분간 잠금 상태로 설정합니다.",
      requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
          required = true,
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = SeatSelectionRequestDTO.class),
              examples = @ExampleObject(
                  name = "좌석 선택 예시",
                  value = """
        {
          "userId": 1,
          "scheduleId": 1,
          "seats": [
            {
              "rowNo": "A",
              "colNo": 5,
              "userType": "Adult"
            }
          ]
        }
        """
              )
          )
      )
  )
  @PostMapping("/select-seats")
  public SeatSelectionResponseDTO selectSeats(@RequestBody SeatSelectionRequestDTO dto) {
    try {
      // 1) 좌석 잠금 + availableSeats 감소 (트랜잭션)
      List<Long> lockIds = seatSelectionService.selectLockIds(dto);

      // 2) 요금 계산 (비트랜잭션)
      List<LockPriceDTO> result = seatReservationService.createReservations(
          dto.getScheduleId(), dto.getUserId(), dto.getSeats(), lockIds
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