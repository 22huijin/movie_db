package com.example.demo.reservation.service;

import com.example.demo.reservation.dto.SeatSelectionRequestDTO;
import com.example.demo.reservation.dto.SeatSelectionResponseDTO;
import com.example.demo.reservation.domain.SeatLock;
import com.example.demo.reservation.repository.SeatLockRepository;
import com.example.demo.schedule.domain.ScheduleSeat;
import com.example.demo.schedule.repository.ScheduleSeatRepository;
import com.example.demo.schedule.repository.ScheduleRepository;
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
  private final ScheduleRepository scheduleRepository;
  private final SeatLockRepository seatLockRepository;

  @Transactional
  public List<Long> selectLockIds(SeatSelectionRequestDTO dto) {
    // 1. 사용자 검증
    User user = userRepository.findById(dto.getUserId())
        .orElseThrow(() -> new IllegalArgumentException("사용자 정보가 없습니다."));

    LocalDateTime now = LocalDateTime.now();
    List<Long> lockIds = new ArrayList<>();

    // 2. 좌석별 처리
    for (var seatInfo : dto.getSeats()) {
      ScheduleSeat ss = scheduleSeatRepository
          .findWithWriteLock(dto.getScheduleId(), seatInfo.getRowNo(), seatInfo.getColNo())
          .orElseThrow(() -> new IllegalArgumentException(
              "존재하지 않는 좌석: " + seatInfo.getRowNo() + "-" + seatInfo.getColNo()
          ));

      // CONFIRMED 체크
      if ("CONFIRMED".equals(ss.getStatus())) {
        throw new IllegalStateException(
            "이미 예약된 좌석: " + seatInfo.getRowNo() + "-" + seatInfo.getColNo()
        );
      }

      // PROCESSING 만료 처리
      if ("PROCESSING".equals(ss.getStatus())) {
        boolean hasActive = !seatLockRepository
            .findByScheduleSeatAndExpiresAtAfter(ss, now)
            .isEmpty();
        if (hasActive) {
          throw new IllegalStateException(
              "이미 선택된 좌석: " + seatInfo.getRowNo() + "-" + seatInfo.getColNo()
          );
        }
        // 만료된 lock 정리 & 상태 복구
        seatLockRepository.deleteByScheduleSeatAndExpiresAtBefore(ss, now);
        ss.setStatus("AVAILABLE");
      }

      // 5.1 상태를 PROCESSING 으로 변경
      ss.setStatus("PROCESSING");
      scheduleSeatRepository.save(ss);

      // 5.2 availableSeats 감소
      var schedule = scheduleRepository.findById(dto.getScheduleId()).get();
      schedule.setAvailableSeats(schedule.getAvailableSeats() - 1);
      scheduleRepository.save(schedule);

      // 5.3 SeatLock 생성
      var lock = new SeatLock();
      lock.setScheduleSeat(ss);
      lock.setUser(user);
      lock.setLockedAt(now);
      lock.setExpiresAt(now.plusMinutes(10));

      lockIds.add(seatLockRepository.save(lock).getLockId());
    }

    return lockIds;
  }
}