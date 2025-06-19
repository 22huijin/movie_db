package com.example.demo.reservation.service;

import com.example.demo.reservation.domain.Reservation;
import com.example.demo.reservation.domain.SeatLock;
import com.example.demo.reservation.repository.ReservationRepository;
import com.example.demo.reservation.repository.SeatLockRepository;
import com.example.demo.schedule.domain.ScheduleSeat;
import com.example.demo.schedule.repository.ScheduleSeatRepository;
import com.example.demo.schedule.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatLockCleanupService {

  private final SeatLockRepository seatLockRepository;
  private final ScheduleSeatRepository scheduleSeatRepository;
  private final ScheduleRepository scheduleRepository;
  private final ReservationRepository reservationRepository;

  /**
   * 만료된 SeatLock, 관련 ScheduleSeat 상태, Reservation까지 복구 및 삭제
   */
  @Scheduled(fixedDelayString = "${seatlock.cleanup.delay:100000}")
  @Transactional
  public void cleanupExpiredLocks() {
    LocalDateTime now = LocalDateTime.now();
    List<SeatLock> expiredLocks = seatLockRepository.findByExpiresAtBefore(now);

    for (SeatLock lock : expiredLocks) {
      ScheduleSeat ss = lock.getScheduleSeat();

      // 1) ScheduleSeat 상태 복구
      ss.setStatus("AVAILABLE");
      scheduleSeatRepository.save(ss);

      // 2) Schedule의 availableSeats 복구
      var schedule = ss.getSchedule();
      schedule.setAvailableSeats(schedule.getAvailableSeats() + 1);
      scheduleRepository.save(schedule);

      // 3) Reservation 삭제 (좌석에 연결된 예약)
      reservationRepository.findByScheduleSeat(ss)
          .ifPresent(reservationRepository::delete);

      // 4) SeatLock 삭제
      seatLockRepository.delete(lock);
    }
  }
}