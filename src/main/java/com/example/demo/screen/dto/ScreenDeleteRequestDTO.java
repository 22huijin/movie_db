package com.example.demo.screen.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ScreenDeleteRequestDTO {

  @Schema(description = "삭제할 상영관 이름", example = "4관", required = true)
  private String screenName;
}