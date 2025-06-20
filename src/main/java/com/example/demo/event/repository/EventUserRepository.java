package com.example.demo.event.repository;

import com.example.demo.event.domain.Event;
import com.example.demo.event.domain.EventUser;
import com.example.demo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventUserRepository extends JpaRepository<EventUser, Long> {
  boolean existsByEventsAndUser(Event events, User user);

  List<EventUser> findByUserOrderByEvents_EventEndDesc(User user);

  List<EventUser> findByEvents(Event event);

  List<EventUser> findByEventsAndApplyStatus(Event event, String applyStatus);
  void deleteAllByEvents_EventId(Long eventId);
}