package com.example.demo.screen.repository;

import com.example.demo.screen.domain.Screen;
import com.example.demo.screen.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
  List<Seat> findAllByScreen(Screen screen);
}