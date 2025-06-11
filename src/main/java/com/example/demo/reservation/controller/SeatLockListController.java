// SeatLockController.java
package com.example.demo.reservation.controller;

import com.example.demo.reservation.dto.SeatLockInfoDto;
import com.example.demo.reservation.service.SeatLockListService;
import com.example.demo.reservation.service.SeatLockListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "예매", description = "예매 및 결제 관련 API")
@RestController
@RequestMapping("/api/seat-lock")
@RequiredArgsConstructor
public class SeatLockListController {

  private final SeatLockListService seatLockService;

  @Operation(summary = "잠금 목록 조회", description = "해당 유저의 유효한 잠금 목록을 반환합니다.")
  @GetMapping("/user/{userId}")
  public List<SeatLockInfoDto> getLockedSeats(
      @Parameter(name = "userId", description = "유저 ID", example = "1")
      @PathVariable(name = "userId") Long userId
  ) {
    return seatLockService.getLockedSeatsByUser(userId);
  }
}