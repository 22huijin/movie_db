package com.example.demo.schedule.service;

import com.example.demo.payment.domain.PricingPolicy;
import com.example.demo.payment.repository.PricingPolicyRepository;
import com.example.demo.schedule.domain.Schedule;
import com.example.demo.schedule.domain.ScheduleSeat;
import com.example.demo.schedule.dto.ScheduleSeatResponseDTO;
import com.example.demo.schedule.dto.ScheduleSeatWithPricingResponseDTO;
import com.example.demo.schedule.repository.ScheduleRepository;
import com.example.demo.schedule.repository.ScheduleSeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleSeatService {

  private final ScheduleSeatRepository scheduleSeatRepository;
  private final ScheduleRepository scheduleRepository;
  private final PricingPolicyRepository pricingPolicyRepository;

  public ScheduleSeatWithPricingResponseDTO getSeatsAndPricingBySchedule(Long scheduleId) {
    // 1. 스케줄 조회
    Schedule schedule = scheduleRepository.findById(scheduleId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상영 일정입니다."));

    LocalDateTime start = schedule.getStartTime();
    DayOfWeek dow = start.getDayOfWeek();
    String dayType = (dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY) ? "Weekend" : "Weekday";

    int hour = start.getHour();
    String timeRange = hour < 6 ? "0-6" : hour < 10 ? "6-10" : "10-24";

    // 2. 가격 조회
    int adultPrice = findPrice(dayType, timeRange, "Adult");
    int teenPrice = findPrice(dayType, timeRange, "Youth");
    int seniorPrice = findPrice(dayType, timeRange, "Senior");

    // 3. 좌석 정렬 및 DTO 변환
    List<ScheduleSeatResponseDTO> seats = scheduleSeatRepository.findBySchedule_ScheduleId(scheduleId)
        .stream()
        .sorted((a, b) -> {
          int rowCompare = Character.compare(a.getSeat().getRow_no(), b.getSeat().getRow_no());
          return rowCompare != 0 ? rowCompare : Integer.compare(a.getSeat().getCol_no(), b.getSeat().getCol_no());
        })
        .map(seat -> new ScheduleSeatResponseDTO(
            seat.getSeat().getRow_no(),
            seat.getSeat().getCol_no(),
            seat.getStatus()
        ))
        .collect(Collectors.toList());

    return new ScheduleSeatWithPricingResponseDTO(seats, adultPrice, teenPrice, seniorPrice);
  }

  private int findPrice(String dayType, String timeRange, String userType) {
    return pricingPolicyRepository.findByDayTypeAndTimeRangeAndUserType(dayType, timeRange, userType)
        .map(PricingPolicy::getPrice)
        .orElseThrow(() -> new IllegalArgumentException("가격 정보가 없습니다."));
  }
}