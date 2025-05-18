package com.example.demo.schedule.repository;

import com.example.demo.schedule.domain.Schedule;
import com.example.demo.schedule.domain.ScheduleSeat;
import com.example.demo.schedule.domain.ScheduleSeatId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import jakarta.persistence.LockModeType;

import java.util.List;
import java.util.Optional;

public interface ScheduleSeatRepository extends JpaRepository<ScheduleSeat, ScheduleSeatId> {
  List<ScheduleSeat> findBySchedule_ScheduleId(Long scheduleId);
  void deleteAllBySchedule(Schedule schedule);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("SELECT ss FROM ScheduleSeat ss " +
      "WHERE ss.schedule.scheduleId = :scheduleId " +
      "AND ss.seat.row_no = :rowNo AND ss.seat.col_no = :colNo")
  Optional<ScheduleSeat> findWithWriteLock(
      @Param("scheduleId") Long scheduleId,
      @Param("rowNo") char rowNo,
      @Param("colNo") int colNo);
}