package com.example.demo.reservation.repository;

import com.example.demo.reservation.domain.SeatLock;
import com.example.demo.schedule.domain.ScheduleSeat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SeatLockRepository extends JpaRepository<SeatLock, Long> {
  List<SeatLock> findByUser_UserId(Long userId);
  List<SeatLock> findByScheduleSeat(ScheduleSeat scheduleSeat);
  List<SeatLock> findByScheduleSeatAndExpiresAtAfter(ScheduleSeat scheduleSeat, LocalDateTime now);
  void deleteByScheduleSeatAndExpiresAtBefore(ScheduleSeat scheduleSeat, LocalDateTime now);
  List<SeatLock> findByExpiresAtBefore(LocalDateTime now);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("SELECT sl FROM SeatLock sl WHERE sl.lockId IN :ids")
  List<SeatLock> findAllByIdForUpdate(@Param("ids") List<Long> ids);
}