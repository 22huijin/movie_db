package com.example.demo.screen.service;

import com.example.demo.screen.domain.Screen;
import com.example.demo.screen.domain.Seat;
import com.example.demo.screen.dto.ScreenDeleteRequestDTO;
import com.example.demo.screen.dto.ScreenRegisterResponseDTO;
import com.example.demo.screen.repository.ScreenRepository;
import com.example.demo.screen.repository.SeatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreenDeletionService {

  private final ScreenRepository screenRepository;
  private final SeatRepository seatRepository;

  @Transactional
  public ScreenRegisterResponseDTO deleteScreenByName(ScreenDeleteRequestDTO dto) {
    String name = dto.getScreenName();

    if (name == null || name.trim().isEmpty()) {
      return new ScreenRegisterResponseDTO(false, "상영관명이 비어 있습니다.");
    }

    Screen screen = screenRepository.findByName(name).orElse(null);
    if (screen == null) {
      return new ScreenRegisterResponseDTO(false, "해당 상영관명이 존재하지 않습니다.");
    }

    List<Seat> seats = screen.getSeats();
    seatRepository.deleteAll(seats); // 좌석 먼저 삭제

    screenRepository.delete(screen); // 그 다음 상영관 삭제

    return new ScreenRegisterResponseDTO(true, "상영관 및 관련 좌석이 삭제되었습니다.");
  }
}