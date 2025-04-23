package com.example.demo.schedule.service;

import com.example.demo.schedule.domain.Schedule;
import com.example.demo.schedule.dto.ScheduleRegisterResponseDTO;
import com.example.demo.schedule.repository.ScheduleRepository;
import com.example.demo.schedule.repository.ScheduleSeatRepository;
import com.example.demo.schedule.dto.ScheduleDeleteRequestDTO;
import com.example.demo.schedule.dto.ScheduleRegisterResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleDeletionService {

  private final ScheduleRepository scheduleRepository;
  private final ScheduleSeatRepository scheduleSeatRepository;

  @Transactional
  public ScheduleRegisterResponseDTO deleteScheduleById(ScheduleDeleteRequestDTO dto) {
    Schedule schedule = scheduleRepository.findById(dto.getScheduleId()).orElse(null);

    if (schedule == null) {
      return new ScheduleRegisterResponseDTO(false, "해당 스케줄 ID가 존재하지 않습니다.");
    }

    // 관련 schedule_seat 먼저 삭제
    scheduleSeatRepository.deleteAllBySchedule(schedule);

    // 스케줄 삭제
    scheduleRepository.delete(schedule);

    return new ScheduleRegisterResponseDTO(true, "상영 스케줄이 삭제되었습니다.");
  }
}