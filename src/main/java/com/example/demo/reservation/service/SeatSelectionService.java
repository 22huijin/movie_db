package com.example.demo.reservation.service;

import com.example.demo.reservation.dto.SeatSelectionRequestDTO;
import com.example.demo.reservation.dto.SeatSelectionResponseDTO;
import com.example.demo.reservation.domain.SeatLock;
import com.example.demo.reservation.repository.SeatLockRepository;
import com.example.demo.schedule.domain.ScheduleSeat;
import com.example.demo.schedule.repository.ScheduleSeatRepository;
import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeatSelectionService {

  private final UserRepository userRepository;
  private final ScheduleSeatRepository scheduleSeatRepository;
  private final SeatLockRepository seatLockRepository;

  @Transactional
  public SeatSelectionResponseDTO selectSeats(SeatSelectionRequestDTO dto) {
    Optional<User> optUser = userRepository.findById(dto.getUserId());
    if (optUser.isEmpty()) {
      return new SeatSelectionResponseDTO(false, "사용자가 존재하지 않습니다.", List.of());
    }
    User user = optUser.get();
    LocalDateTime now = LocalDateTime.now();
    List<Long> lockIds = new ArrayList<>();

    for (SeatSelectionRequestDTO.SeatInfo seatInfo : dto.getSeats()) {
      ScheduleSeat ss = scheduleSeatRepository
          .findWithWriteLock(dto.getScheduleId(), seatInfo.getRowNo(), seatInfo.getColNo())
          .orElse(null);

      if (ss == null) {
        return new SeatSelectionResponseDTO(false,
            "존재하지 않는 좌석입니다: " + seatInfo.getRowNo() + "-" + seatInfo.getColNo(), List.of());
      }

      if ("CONFIRMED".equals(ss.getStatus())) {
        return new SeatSelectionResponseDTO(false,
            "이미 예약된 좌석입니다: " + seatInfo.getRowNo() + "-" + seatInfo.getColNo(), List.of());
      }

      if ("PENDING".equals(ss.getStatus())) {
        var activeLocks = seatLockRepository.findByScheduleSeatAndExpiresAtAfter(ss, now);
        if (!activeLocks.isEmpty()) {
          return new SeatSelectionResponseDTO(false,
              "이미 선택된 좌석입니다: " + seatInfo.getRowNo() + "-" + seatInfo.getColNo(), List.of());
        }
        seatLockRepository.deleteByScheduleSeatAndExpiresAtBefore(ss, now);
        ss.setStatus("AVAILABLE");
      }

      ss.setStatus("PENDING");
      ScheduleSeat saved = scheduleSeatRepository.save(ss);

      SeatLock lock = new SeatLock();
      lock.setScheduleSeat(saved);
      lock.setUser(user);
      lock.setLockedAt(now);
      lock.setExpiresAt(now.plusMinutes(10));

      lockIds.add(seatLockRepository.save(lock).getLockId());
    }

    return new SeatSelectionResponseDTO(true,
        "10분 이내에 결제하셔야 예약이 완료됩니다.", lockIds);
  }
}