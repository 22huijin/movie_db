package com.example.demo.event.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EventWinnerDto {
  private Long userId;
  private String userName;
  private String userEmail;
}