package com.example.demo.schedule.repository;

import com.example.demo.schedule.domain.Schedule;
import com.example.demo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
  @Override
  Optional<Schedule> findById(Long id);
}