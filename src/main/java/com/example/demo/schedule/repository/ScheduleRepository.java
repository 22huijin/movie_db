package com.example.demo.schedule.repository;

import com.example.demo.schedule.domain.Schedule;
import com.example.demo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
  @Override
  Optional<Schedule> findById(Long id);

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