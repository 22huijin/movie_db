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

  private final ScheduleSeatRepository scheduleSeatRepository;

  public List<ScheduleSeatResponseDTO> getSeatsBySchedule(Long scheduleId) {
    // schedule_id 기준으로 전체 좌석 조회 후 정렬
    List<ScheduleSeat> scheduleSeats = scheduleSeatRepository.findBySchedule_ScheduleId(scheduleId)
        .stream()
        .sorted((a, b) -> {
          int rowCompare = Character.compare(a.getSeat().getRow_no(), b.getSeat().getRow_no());
          if (rowCompare != 0) return rowCompare;
          return Integer.compare(a.getSeat().getCol_no(), b.getSeat().getCol_no());
        })
        .toList();


    // DTO 변환
    return scheduleSeats.stream()
        .map(seat -> new ScheduleSeatResponseDTO(
            seat.getSeat().getRow_no(),
            seat.getSeat().getCol_no(),
            seat.getStatus()
        ))
        .collect(Collectors.toList());
  }
}