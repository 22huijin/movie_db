package com.example.demo.event.repository;

import com.example.demo.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByEventEndGreaterThanEqual(LocalDate date); // 진행중
    List<Event> findByEventEndLessThan(LocalDate date);
}