package com.example.demo.reservation.service;

import com.example.demo.payment.domain.PricingPolicy;
import com.example.demo.payment.repository.PricingPolicyRepository;
import com.example.demo.reservation.dto.LockPriceDTO;
import com.example.demo.reservation.dto.SeatSelectionRequestDTO;
import com.example.demo.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class SeatReservationService {
  private final ScheduleRepository scheduleRepository;
  private final PricingPolicyRepository pricingPolicyRepository;

  public List<LockPriceDTO> calculatePrices(
      Long scheduleId,
      List<SeatSelectionRequestDTO.SeatInfo> seats,
      List<Long> lockIds
  ) {
    // 스케줄에서 상영시간 가져오기
    LocalDateTime start = scheduleRepository.findById(scheduleId)
        .orElseThrow().getStartTime();

    // 요일 타입 결정
    DayOfWeek dow = start.getDayOfWeek();
    String dayType = (dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY)
        ? "Weekend" : "Weekday";

    // 시간 구간 결정
    int hour = start.getHour();
    String timeRange = hour < 6   ? "0-6"
        : hour < 10  ? "6-10"
        : "10-24";

    // lockIds ↔ seats 인덱스 일치 가정
    return IntStream.range(0, lockIds.size())
        .mapToObj(i -> {
          String userType = seats.get(i).getUserType();
          PricingPolicy policy = pricingPolicyRepository
              .findByDayTypeAndTimeRangeAndUserType(dayType, timeRange, userType)
              .orElseThrow(() -> new IllegalArgumentException(
                  "해당 요금정책이 없습니다: " + dayType + "/" + timeRange + "/" + userType
              ));
          return new LockPriceDTO(lockIds.get(i), policy.getPrice());
        })
        .toList();
  }
}