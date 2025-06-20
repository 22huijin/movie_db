package com.example.demo.event.controller;

import com.example.demo.event.dto.EventRegisterRequestDTO;
import com.example.demo.event.service.EventRegisterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
@Tag(name = "이벤트", description = "이벤트 조회 및 관리 API")
public class EventRegisterController {

    private final EventRegisterService eventRegisterService;

    @Operation(summary = "이벤트 등록", description = "관리자가 관련 정보를 입력받아 이벤트를 등록합니다.")
    @PostMapping("/register")
    public ResponseEntity<Long> registerEvent(@RequestBody EventRegisterRequestDTO dto) {
        Long eventId = eventRegisterService.registerEvent(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventId);
    }
}





