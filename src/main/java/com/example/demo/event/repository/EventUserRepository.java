package com.example.demo.event.repository;

import com.example.demo.event.domain.Event;
import com.example.demo.event.domain.EventUser;
import com.example.demo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventUserRepository extends JpaRepository<EventUser, Long> {
  boolean existsByEventsAndUser(Event events, User user);
}