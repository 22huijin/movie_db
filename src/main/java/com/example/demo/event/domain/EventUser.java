package com.example.demo.event.domain;

import com.example.demo.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "events_user")
@Getter
@Setter
public class EventUser {
  @Id
  private Long eventUserId;

  @ManyToOne
  @JoinColumn(name = "event_id", nullable = false)
  private Event events;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  private String applyStatus;
}