package com.example.demo.reservation.repository;

import com.example.demo.reservation.domain.SeatLock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatLockRepository extends JpaRepository<SeatLock, Long> {
}