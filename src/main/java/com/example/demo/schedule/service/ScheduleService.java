// 기능: 상영 시간표 데이터를 조건에 따라 조회하고 DTO로 변환하는 서비스
package com.example.demo.schedule.service;

import com.example.demo.schedule.domain.Schedule;
import com.example.demo.schedule.dto.ScheduleResponseDTO;
import com.example.demo.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleService {
  ZoneId zoneId = ZoneId.of("Asia/Seoul");
  LocalDateTime now = LocalDateTime.now(zoneId);       // 현 시각
  LocalDate today = LocalDate.now(zoneId);             // 오늘 날짜

  private final ScheduleRepository scheduleRepository;

  public List<ScheduleResponseDTO> getScheduleListByDate(LocalDate date) {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime startOfDay = date.atStartOfDay();
    LocalDateTime startOfNextDay = date.plusDays(1).atStartOfDay();

    List<Schedule> schedules = scheduleRepository.findAllSchedulesOfDay(startOfDay, startOfNextDay);

    log.info("요청 날짜: {}", date);
    log.info("현재 날짜: {}", today);
    log.info("필터링 전: {}건", schedules.size());
    log.info("필터링 후: {}건", schedules.stream().filter(s -> s.getStartTime().isAfter(now)).count());

    // 오늘이라면 현재 시간 이후만 필터
    if (date.equals(today)) {
      schedules = schedules.stream()
          .filter(s -> s.getStartTime().isAfter(now))
          .toList();
    }

    return schedules.stream()
        .map(s -> new ScheduleResponseDTO(
            s.getMovie().getTitle(),
            s.getScreen().getName(),
            s.getStartTime().toString(),
            s.getEndTime().toString(),
            s.getAvailableSeats(),
            s.getPrice()
        ))
        .toList();
  }
}