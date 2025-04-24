package com.example.demo.schedule.repository;

import com.example.demo.schedule.domain.Schedule;
import com.example.demo.schedule.domain.ScheduleSeat;
import com.example.demo.schedule.domain.ScheduleSeatId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleSeatRepository extends JpaRepository<ScheduleSeat, ScheduleSeatId> {
  List<ScheduleSeat> findBySchedule_ScheduleId(Long scheduleId);
  void deleteAllBySchedule(Schedule schedule);
}