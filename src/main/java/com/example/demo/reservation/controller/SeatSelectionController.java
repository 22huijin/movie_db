package com.example.demo.reservation.controller;

import com.example.demo.reservation.dto.SeatSelectionRequestDTO;
import com.example.demo.reservation.dto.SeatSelectionResponseDTO;
import com.example.demo.reservation.service.SeatSelectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "좌석 선택", description = "좌석을 PENDING 상태로 변경하고 lock 레코드를 생성합니다.")
@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class SeatSelectionController {

  private final SeatSelectionService seatSelectionService;

  @Operation(summary = "좌석 선택 및 잠금", description = "좌석을 선택하여 10분간 잠금 상태로 설정합니다.")
  @PostMapping("/select-seats")
  public SeatSelectionResponseDTO selectSeats(@RequestBody SeatSelectionRequestDTO dto) {
    return seatSelectionService.selectSeats(dto);
  }
}