package com.example.demo.schedule.service;

import com.example.demo.schedule.domain.ScheduleSeat;
import com.example.demo.schedule.dto.ScheduleSeatResponseDTO;
import com.example.demo.schedule.repository.ScheduleSeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleSeatService {
  private final ScheduleSeatRepository reservedSeatRepository;

  public List<ScheduleSeatResponseDTO> getSeatsBySchedule(Long scheduleId) {
    List<ScheduleSeat> scheduleSeats = reservedSeatRepository.findBySchedule_ScheduleId(scheduleId)
        .stream()
        .sorted((a, b) -> {
          int rowCompare = Character.compare(a.getSeat().getRow_no(), b.getSeat().getRow_no());
          if (rowCompare != 0) return rowCompare;
          return Integer.compare(a.getSeat().getCol_no(), b.getSeat().getCol_no());
        })
        .collect(Collectors.toList());

    return scheduleSeats.stream()
        .map(seat -> new ScheduleSeatResponseDTO(
            seat.getSeat().getRow_no(),
            seat.getSeat().getCol_no(),
            seat.getStatus()
        ))
        .collect(Collectors.toList());
  }
}