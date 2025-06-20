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
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_user_seq_gen")
  @SequenceGenerator(name = "event_user_seq_gen", sequenceName = "EVENT_USER_SEQ", allocationSize = 1)
  private Long eventUserId;

  @ManyToOne
  @JoinColumn(name = "event_id", nullable = false)
  private Event events;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  private String applyStatus;
}