package com.example.demo.event.repository;

import com.example.demo.event.domain.EventUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventUserRepository extends JpaRepository<EventUser, Long> {
}