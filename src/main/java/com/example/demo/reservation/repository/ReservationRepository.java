package com.example.demo.reservation.repository;

import com.example.demo.reservation.domain.Reservation;
import com.example.demo.schedule.domain.ScheduleSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
  Optional<Reservation> findByScheduleSeat(ScheduleSeat scheduleSeat);
}