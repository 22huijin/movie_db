package com.example.demo.event.service;

import com.example.demo.event.domain.Event;
import com.example.demo.event.domain.EventUser;
import com.example.demo.event.dto.EventApplyRequestDto;
import com.example.demo.event.dto.EventApplyResponseDto;
import com.example.demo.event.repository.EventRepository;
import com.example.demo.event.repository.EventUserRepository;
import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EventApplyService {

  private final EventRepository eventRepository;
  private final UserRepository userRepository;
  private final EventUserRepository eventUserRepository;

  public EventApplyService(EventRepository eventRepository, UserRepository userRepository, EventUserRepository eventUserRepository) {
    this.eventRepository = eventRepository;
    this.userRepository = userRepository;
    this.eventUserRepository = eventUserRepository;
  }

  @Transactional
  public EventApplyResponseDto applyToEvent(EventApplyRequestDto dto) {
    Event event = eventRepository.findById(dto.getEventId())
        .orElseThrow(() -> new IllegalArgumentException("해당 이벤트가 존재하지 않습니다."));

    LocalDate today = LocalDate.now();
    if (today.isBefore(event.getEventStart()) || today.isAfter(event.getEventEnd())) {
      return new EventApplyResponseDto(false, "이벤트 기간이 아닙니다. 응모가 반려되었습니다.");
    }

    User user = userRepository.findById(dto.getUserId())
        .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

    boolean alreadyApplied = eventUserRepository.existsByEventsAndUser(event, user);
    if (alreadyApplied) {
      return new EventApplyResponseDto(false, "이미 이 이벤트에 응모하셨습니다.");
    }

    EventUser eventUser = new EventUser();
    eventUser.setEvents(event);
    eventUser.setUser(user);
    eventUser.setApplyStatus(""); // 초기 상태

    eventUserRepository.save(eventUser);

    return new EventApplyResponseDto(true, "이벤트 응모가 완료되었습니다.");
  }
}