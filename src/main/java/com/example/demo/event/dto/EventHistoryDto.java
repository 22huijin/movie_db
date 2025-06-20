package com.example.demo.event.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class EventHistoryDto {
  private Long eventId;
  private String eventName;
  private LocalDate eventStart;
  private LocalDate eventEnd;
  private String eventThumbnailUrl;
  private Integer maxWinners;
  private String applyStatus;
}