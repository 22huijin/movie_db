package com.example.demo.event.service;

import com.example.demo.event.repository.EventRepository;

import com.example.demo.event.repository.EventUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventDeleteService {

    private final EventRepository eventRepository;
    private final EventUserRepository eventUserRepository;

    @Transactional
    public void deleteEventWithParticipants(Long eventId) {
        // 먼저 event_user 삭제
        eventUserRepository.deleteAllByEvents_EventId(eventId);

        // 그 다음 event 삭제
        eventRepository.deleteById(eventId);
    }
}
