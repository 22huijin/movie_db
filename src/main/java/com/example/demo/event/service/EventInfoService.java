package com.example.demo.event.service;

import com.example.demo.event.domain.Event;
import com.example.demo.event.dto.EventInfoResponseDTO;
import com.example.demo.event.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventInfoService {

    private final EventRepository eventRepository;

    public EventInfoService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventInfoResponseDTO> getEventsByStatus(String status) {
        LocalDate today = LocalDate.now();
        List<Event> events;

        if (status.equalsIgnoreCase("진행중")) {
            events = eventRepository.findByEventEndGreaterThanEqual(today);
        } else if (status.equalsIgnoreCase("종료")) {
            events = eventRepository.findByEventEndLessThan(today);
        } else {
            throw new IllegalArgumentException("상태는 '진행중' 또는 '종료'만 가능합니다.");
        }

        return events.stream().map(EventInfoResponseDTO::new).toList();
    }
}

