package com.example.demo.reservation.repository;

import com.example.demo.reservation.domain.ReservedSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservedSeatRepository extends JpaRepository<ReservedSeat, Long> {
}