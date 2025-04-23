// 기능: Schedule 정보를 프론트에 전송할 형태로 변환하는 DTO
package com.example.demo.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleResponseDTO {
  private String movieTitle;       // 영화 제목
  private String screenName;       // 상영관 이름
  private String startTime;        // 상영 시작 시간
  private String endTime;          // 상영 종료 시간
  private int availableSeats;      // 잔여 좌석 수
  private int price;               // 가격
}