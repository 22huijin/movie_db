package com.example.demo.reservation.domain;

import com.example.demo.schedule.domain.Schedule;
import com.example.demo.user.domain.User;
import com.example.demo.payment.domain.Payment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Reservation {
  @Id
  @GeneratedValue
  private Long reservationId;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "schedule_id")
  private Schedule schedule;

  private LocalDateTime reservationTime;
  private String status;

  @OneToMany(mappedBy = "reservation")
  private List<ReservedSeat> reservedSeats;

  @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
  private Payment payment;
}