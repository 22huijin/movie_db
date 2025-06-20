package com.example.demo.event.service;

import com.example.demo.event.domain.Event;
import com.example.demo.event.domain.EventUser;
import com.example.demo.event.dto.EventSelectResponseDto;
import com.example.demo.event.repository.EventRepository;
import com.example.demo.event.repository.EventUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class EventSelectService {

  private final EventRepository eventRepository;
  private final EventUserRepository eventUserRepository;

  public EventSelectService(EventRepository eventRepository, EventUserRepository eventUserRepository) {
    this.eventRepository = eventRepository;
    this.eventUserRepository = eventUserRepository;
  }

  @Transactional
  public EventSelectResponseDto selectWinners(Long eventId) {
    Event event = eventRepository.findById(eventId)
        .orElseThrow(() -> new IllegalArgumentException("해당 이벤트가 존재하지 않습니다."));

    if (LocalDate.now().isBefore(event.getEventEnd())) {
      return new EventSelectResponseDto(false, "이벤트가 아직 종료되지 않았습니다.");
    }

    boolean alreadyDrawn = eventUserRepository
        .existsByEventsAndApplyStatus(event, "SELECTED");
    if (alreadyDrawn) {
      return new EventSelectResponseDto(false, "이미 당첨자가 선정된 이벤트입니다.");
    }

    List<EventUser> applicants = eventUserRepository.findByEvents(event);
    int totalApplicants = applicants.size();
    int maxWinners = event.getMaxWinners();

    if (totalApplicants == 0) {
      return new EventSelectResponseDto(false, "응모자가 존재하지 않습니다.");
    }

    // 무작위 섞기
    Collections.shuffle(applicants);

    // SELECTED vs NOT_SELECTED 구분
    for (int i = 0; i < totalApplicants; i++) {
      EventUser eu = applicants.get(i);
      eu.setApplyStatus(i < maxWinners ? "SELECTED" : "NOT_SELECTED");
    }

    return new EventSelectResponseDto(true,
        totalApplicants <= maxWinners ?
            "모든 응모자가 당첨 처리되었습니다." :
            String.format("총 %d명의 당첨자가 선정되었습니다.", maxWinners));
  }
}