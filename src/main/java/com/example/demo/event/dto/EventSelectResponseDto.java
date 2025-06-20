package com.example.demo.event.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EventSelectResponseDto {
  private boolean success;
  private String message;
}