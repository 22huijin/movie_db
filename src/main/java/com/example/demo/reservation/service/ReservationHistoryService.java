package com.example.demo.reservation.service;

import com.example.demo.reservation.dto.ReservationHistoryResponseDTO;
import com.example.demo.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationHistoryService {

  private final ReservationRepository reservationRepository;

  public List<ReservationHistoryResponseDTO> getReservationHistory(Long userId) {
    return reservationRepository.findDetailedReservationsByUserId(userId).stream()
        .map(reservation -> {
          var seat = reservation.getScheduleSeat().getSeat();
          var schedule = reservation.getScheduleSeat().getSchedule();
          var movie = schedule.getMovie();
          var screen = schedule.getScreen();
          return new ReservationHistoryResponseDTO(
              reservation.getReservationId(),
              movie.getMovieId(),
              movie.getTitle(),
              screen.getName(),
              schedule.getStartTime(),
              String.valueOf(seat.getRow_no()),
              seat.getCol_no(),
              reservation.getUpdateTime(),
              reservation.getStatus()
          );
        })
        .collect(Collectors.toList());
  }
}
