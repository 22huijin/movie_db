package com.example.demo.event.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventApplyRequestDto {
  private Long eventId;
  private Long userId;
}