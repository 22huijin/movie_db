package com.example.demo.reservation.repository;

import com.example.demo.reservation.domain.Reservation;
import com.example.demo.schedule.domain.ScheduleSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
  Optional<Reservation> findByScheduleSeat(ScheduleSeat scheduleSeat);

  @Query("""
        SELECT r FROM Reservation r
        JOIN FETCH r.scheduleSeat ss
        JOIN FETCH ss.schedule s
        JOIN FETCH s.movie m
        JOIN FETCH s.screen sc
        JOIN FETCH ss.seat seat
        WHERE r.user.userId = :userId AND r.status != 'AVAILABLE'
        ORDER BY r.updateTime DESC
    """)
  List<Reservation> findDetailedReservationsByUserId(@Param("userId") Long userId);
}