// 기능: 특정 날짜에 시작하며 현재 시간 이후 상영되는 스케줄 조회
package com.example.demo.schedule.repository;

import com.example.demo.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

  @Query("SELECT s FROM Schedule s " +
      "WHERE s.startTime >= :startOfDay AND s.startTime < :startOfNextDay " +
      "ORDER BY s.startTime")
  List<Schedule> findAllSchedulesOfDay(
      @Param("startOfDay") LocalDateTime startOfDay,
      @Param("startOfNextDay") LocalDateTime startOfNextDay);

  @Query("SELECT s FROM Schedule s " +
      "WHERE s.screen.screenId = :screenId " +
      "AND (:endTime > s.startTime AND :startTime < s.endTime)")
  List<Schedule> findConflictingSchedules(
      @Param("screenId") Long screenId,
      @Param("startTime") LocalDateTime startTime,
      @Param("endTime") LocalDateTime endTime);
}
