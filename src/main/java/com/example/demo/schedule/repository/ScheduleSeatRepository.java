package com.example.demo.schedule.repository;

import com.example.demo.schedule.domain.ScheduleSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleSeatRepository extends JpaRepository<ScheduleSeat, Long> {
}