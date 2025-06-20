package com.example.demo.event.controller;

import com.example.demo.event.service.EventDeleteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
@Tag(name = "이벤트", description = "이벤트 조회 및 관리 API")
public class EventDeleteController {

    private final EventDeleteService eventDeleteService;

    @DeleteMapping("/delete/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long eventId) {
        eventDeleteService.deleteEventWithParticipants(eventId);
        return ResponseEntity.noContent().build();
    }
}

