package com.example.demo.event.service;

import com.example.demo.event.domain.Event;
import com.example.demo.event.domain.EventUser;
import com.example.demo.event.dto.EventWinnerDto;
import com.example.demo.event.repository.EventRepository;
import com.example.demo.event.repository.EventUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventWinnerService {

  private final EventRepository eventRepository;
  private final EventUserRepository eventUserRepository;

  public EventWinnerService(EventRepository eventRepository, EventUserRepository eventUserRepository) {
    this.eventRepository = eventRepository;
    this.eventUserRepository = eventUserRepository;
  }

  public List<EventWinnerDto> getWinnersByEventId(Long eventId) {
    Event event = eventRepository.findById(eventId)
        .orElseThrow(() -> new IllegalArgumentException("해당 이벤트가 존재하지 않습니다."));

    List<EventUser> selected = eventUserRepository.findByEventsAndApplyStatus(event, "SELECTED");

    return selected.stream()
        .map(eu -> new EventWinnerDto(
            eu.getUser().getUserId(),
            eu.getUser().getNickname(),
            eu.getUser().getEmail()
        ))
        .collect(Collectors.toList());
  }
}