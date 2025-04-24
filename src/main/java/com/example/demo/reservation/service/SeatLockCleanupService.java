package com.example.demo.reservation.service;

import com.example.demo.reservation.domain.SeatLock;
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

  private final SeatLockRepository      seatLockRepository;
  private final ScheduleSeatRepository  scheduleSeatRepository;
  private final ScheduleRepository      scheduleRepository;

  /**
   * 10분마다(기본) 만료된 SeatLock을 정리합니다.
   * @Scheduled cron, fixedDelay 등 원하는 주기로 변경 가능
   */
  @Scheduled(fixedDelayString = "${seatlock.cleanup.delay:600000}") // 600,000ms = 10분
  @Transactional
  public void cleanupExpiredLocks() {
    LocalDateTime now = LocalDateTime.now();
    List<SeatLock> expiredLocks = seatLockRepository.findByExpiresAtBefore(now);

    for (SeatLock lock : expiredLocks) {
      ScheduleSeat ss = lock.getScheduleSeat();

      // 1) ScheduleSeat 상태 복구
      ss.setStatus("AVAILABLE");
      scheduleSeatRepository.save(ss);

      // 2) Schedule.availableSeats 복구
      var schedule = ss.getSchedule();
      schedule.setAvailableSeats(schedule.getAvailableSeats() + 1);
      scheduleRepository.save(schedule);

      // 3) SeatLock 삭제
      seatLockRepository.delete(lock);
    }
  }
}