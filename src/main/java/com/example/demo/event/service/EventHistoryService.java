package com.example.demo.event.service;

import com.example.demo.event.domain.EventUser;
import com.example.demo.event.dto.EventHistoryDto;
import com.example.demo.event.repository.EventUserRepository;
import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventHistoryService {

  private final EventUserRepository eventUserRepository;
  private final UserRepository userRepository;

  public EventHistoryService(EventUserRepository eventUserRepository, UserRepository userRepository) {
    this.eventUserRepository = eventUserRepository;
    this.userRepository = userRepository;
  }

  public List<EventHistoryDto> getEventHistoryByUserId(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

    List<EventUser> eventUsers = eventUserRepository.findByUserOrderByEvents_EventEndDesc(user);

    return eventUsers.stream()
        .map(eu -> {
          var e = eu.getEvents();
          return new EventHistoryDto(
              e.getEventId(),
              e.getEventName(),
              e.getEventStart(),
              e.getEventEnd(),
              e.getEventThumbnailUrl(),
              e.getMaxWinners(),
              eu.getApplyStatus()
          );
        })
        .collect(Collectors.toList());
  }
}