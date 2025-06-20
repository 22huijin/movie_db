package com.example.demo.event.controller;

import com.example.demo.event.dto.EventInfoResponseDTO;
import com.example.demo.event.service.EventInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
@Tag(name = "이벤트", description = "이벤트 조회 및 관리 API")
public class EventInfoController {

    private final EventInfoService eventService;

    public EventInfoController(EventInfoService eventService) {
        this.eventService = eventService;
    }

    @Operation(summary = "진행중/종료 이벤트 조회", description = "진행중이거나 종료된 이벤트 정보를 조회합니다.")
    @GetMapping("/info")
    public List<EventInfoResponseDTO> getEventsByStatus(@RequestParam String status) {
        return eventService.getEventsByStatus(status);
    }
}

