package com.example.demo.event.dto;

import com.example.demo.event.domain.Event;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class EventInfoResponseDTO {
    private final Long eventId;
    private final String eventName;
    private final LocalDate eventStart;
    private final LocalDate eventEnd;
    private final String eventThumbnailUrl;

    @Builder
    public EventInfoResponseDTO(Event event) {
        this.eventId = event.getEventId();
        this.eventName = event.getEventName();
        this.eventStart = event.getEventStart();
        this.eventEnd = event.getEventEnd();
        this.eventThumbnailUrl = event.getEventThumbnailUrl();
    }
}

