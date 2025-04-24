package com.example.demo.reservation.domain;

import com.example.demo.schedule.domain.ScheduleSeat;
import com.example.demo.user.domain.User;
import com.example.demo.payment.domain.PricingPolicy;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "RESERVATION")
@Getter
@Setter
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long reservationId;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumns({
      @JoinColumn(name = "schedule_id", referencedColumnName = "schedule_id"),
      @JoinColumn(name = "seat_id", referencedColumnName = "seat_id")
  })
  private ScheduleSeat scheduleSeat;

  @ManyToOne
  @JoinColumn(name = "price_id", nullable = false)
  private PricingPolicy pricingPolicy;

  private LocalDateTime updateTime;

  private String status;
}