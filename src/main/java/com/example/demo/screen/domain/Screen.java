package com.example.demo.screen.domain;

import com.example.demo.schedule.domain.Schedule;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Screen {
  @Id
  @GeneratedValue
  private Long screenId;

  private String name;
  private int totalSeats; //삭제?

  @OneToMany(mappedBy = "screen")
  private List<Seat> seats;

  @OneToMany(mappedBy = "screen")
  private List<Schedule> schedules;
}