package com.example.demo.event.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class EventRegisterRequestDTO {
    private String eventName;
    private LocalDate eventStart;
    private LocalDate eventEnd;
    private String eventThumbnailUrl;
    private Long movieId;
    private Integer maxWinners;
}
