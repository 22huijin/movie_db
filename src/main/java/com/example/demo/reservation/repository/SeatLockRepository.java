package com.example.demo.reservation.repository;

import com.example.demo.reservation.domain.SeatLock;
import com.example.demo.schedule.domain.ScheduleSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SeatLockRepository extends JpaRepository<SeatLock, Long> {
  List<SeatLock> findByScheduleSeat(ScheduleSeat scheduleSeat);
  List<SeatLock> findByScheduleSeatAndExpiresAtAfter(ScheduleSeat scheduleSeat, LocalDateTime now);
  void deleteByScheduleSeatAndExpiresAtBefore(ScheduleSeat scheduleSeat, LocalDateTime now);
}