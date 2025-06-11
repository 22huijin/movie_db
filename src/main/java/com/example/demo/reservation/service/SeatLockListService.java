// SeatLockService.java
package com.example.demo.reservation.service;

import com.example.demo.reservation.domain.Reservation;
import com.example.demo.reservation.domain.SeatLock;
import com.example.demo.reservation.dto.SeatLockInfoDto;
import com.example.demo.reservation.repository.ReservationRepository;
import com.example.demo.reservation.repository.SeatLockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeatLockListService {

  private final SeatLockRepository seatLockRepository;
  private final ReservationRepository reservationRepository;

  public List<SeatLockInfoDto> getLockedSeatsByUser(Long userId) {
    List<SeatLock> locks = seatLockRepository.findByUser_UserId(userId);

    return locks.stream().map(lock -> {
      var scheduleSeat = lock.getScheduleSeat();
      var schedule = scheduleSeat.getSchedule();
      var seat = scheduleSeat.getSeat();

      // 가격 가져오기 (있을 경우)
      Reservation reservation = reservationRepository
          .findByScheduleSeat(scheduleSeat)
          .orElse(null);

      int price = (reservation != null)
          ? reservation.getPricingPolicy().getPrice()
          : 0;

      return new SeatLockInfoDto(
          schedule.getMovie().getTitle(),
          schedule.getScreen().getName(),
          schedule.getStartTime(),
          seat.getRow_no(),
          seat.getCol_no(),
          lock.getExpiresAt(),
          price,
          lock.getLockId()
      );
    }).collect(Collectors.toList());
  }
}