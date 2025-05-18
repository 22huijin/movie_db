package com.example.demo.reservation.service;

import com.example.demo.payment.domain.PricingPolicy;
import com.example.demo.payment.repository.PricingPolicyRepository;
import com.example.demo.reservation.domain.Reservation;
import com.example.demo.reservation.dto.LockPriceDTO;
import com.example.demo.reservation.dto.SeatSelectionRequestDTO;
import com.example.demo.reservation.repository.ReservationRepository;
import com.example.demo.schedule.domain.ScheduleSeat;
import com.example.demo.schedule.repository.ScheduleRepository;
import com.example.demo.schedule.repository.ScheduleSeatRepository;
import com.example.demo.screen.repository.SeatRepository;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class SeatReservationService {

  private final ScheduleRepository scheduleRepository;
  private final ScheduleSeatRepository scheduleSeatRepository;
  private final SeatRepository seatRepository;
  private final PricingPolicyRepository pricingPolicyRepository;
  private final ReservationRepository reservationRepository;
  private final UserRepository userRepository;

  @Transactional
  public List<LockPriceDTO> createReservations(
      Long scheduleId,
      Long userId,
      List<SeatSelectionRequestDTO.SeatInfo> seats,
      List<Long> lockIds
  ) {
    LocalDateTime start = scheduleRepository.findById(scheduleId)
        .orElseThrow().getStartTime();

    DayOfWeek dow = start.getDayOfWeek();
    String dayType = (dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY)
        ? "Weekend" : "Weekday";

    int hour = start.getHour();
    String timeRange = hour < 6 ? "0-6" : hour < 10 ? "6-10" : "10-24";

    return IntStream.range(0, lockIds.size())
        .mapToObj(i -> {
          String userType = seats.get(i).getUserType();
          PricingPolicy policy = pricingPolicyRepository
              .findByDayTypeAndTimeRangeAndUserType(dayType, timeRange, userType)
              .orElseThrow(() -> new IllegalArgumentException(
                  "해당 요금정책이 없습니다: " + dayType + "/" + timeRange + "/" + userType
              ));

          // Reservation 저장
          var seatInfo = seats.get(i);
          var user = userRepository.findById(userId)
              .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));
          var ss = scheduleSeatRepository
              .findWithWriteLock(scheduleId, seatInfo.getRowNo(), seatInfo.getColNo())
              .orElseThrow(() -> new IllegalArgumentException(
                  "존재하지 않는 좌석: " + seatInfo.getRowNo() + "-" + seatInfo.getColNo()
              ));

          var reservation = new Reservation();
          reservation.setUser(user);
          reservation.setScheduleSeat(ss);
          reservation.setPricingPolicy(policy);
          reservation.setUpdateTime(LocalDateTime.now());
          reservation.setStatus("AVAILABLE");

          reservationRepository.save(reservation);

          return new LockPriceDTO(lockIds.get(i), policy.getPrice());
        })
        .toList();
  }
}