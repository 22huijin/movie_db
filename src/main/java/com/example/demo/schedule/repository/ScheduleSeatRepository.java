package com.example.demo.schedule.repository;

import com.example.demo.schedule.domain.Schedule;
import com.example.demo.schedule.domain.ScheduleSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleSeatRepository extends JpaRepository<ScheduleSeat, Long> {
  List<ScheduleSeat> findAllBySchedule(Schedule schedule);
  void deleteAllBySchedule(Schedule schedule);
}