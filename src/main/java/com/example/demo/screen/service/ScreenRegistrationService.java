package com.example.demo.screen.service;

import com.example.demo.screen.domain.Screen;
import com.example.demo.screen.domain.Seat;
import com.example.demo.screen.dto.ScreenRegisterRequestDTO;
import com.example.demo.screen.dto.ScreenRegisterResponseDTO;
import com.example.demo.screen.repository.ScreenRepository;
import com.example.demo.screen.repository.SeatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

// 기능: 상영관 및 좌석 등록을 담당하는 별도의 서비스
@Service
@RequiredArgsConstructor
public class ScreenRegistrationService {

  private final ScreenRepository screenRepository;
  private final SeatRepository seatRepository;

  @Transactional
  public ScreenRegisterResponseDTO registerScreen(ScreenRegisterRequestDTO dto) {
    if (dto.getScreenName() == null || dto.getScreenName().trim().isEmpty()) {
      return new ScreenRegisterResponseDTO(false, "상영관명이 비어 있습니다.");
    }

    if (dto.getSeats() == null || dto.getSeats().isEmpty()) {
      return new ScreenRegisterResponseDTO(false, "좌석 정보가 존재하지 않습니다.");
    }

    if (screenRepository.findByName(dto.getScreenName()).isPresent()) {
      return new ScreenRegisterResponseDTO(false, "이미 등록된 상영관명입니다.");
    }

    Set<String> seatKeySet = new HashSet<>();
    for (var seat : dto.getSeats()) {
      String key = seat.getRowNo() + "-" + seat.getColNo();
      if (!seatKeySet.add(key)) {
        return new ScreenRegisterResponseDTO(false, "row_no과 col_no이 동일한 좌석이 있습니다.");
      }
    }

    Screen screen = new Screen();
    screen.setName(dto.getScreenName());
    screen.setTotalSeats(dto.getSeats().size());
    Screen savedScreen = screenRepository.save(screen);

    List<Seat> seats = dto.getSeats().stream().map(reqSeat -> {
      Seat seat = new Seat();
      seat.setScreen(savedScreen);
      seat.setRow_no(reqSeat.getRowNo());
      seat.setCol_no(reqSeat.getColNo());
      return seat;
    }).collect(Collectors.toList());

    seatRepository.saveAll(seats);

    return new ScreenRegisterResponseDTO(true, "상영관 및 좌석 등록에 성공했습니다.");
  }
}