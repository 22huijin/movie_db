package com.example.demo.screen.repository;

import com.example.demo.screen.domain.Seat;
import com.example.demo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}