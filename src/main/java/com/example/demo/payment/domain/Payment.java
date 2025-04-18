package com.example.demo.payment.domain;

import com.example.demo.reservation.domain.Reservation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Payment {
  @Id
  @GeneratedValue
  private Long paymentId;

  @OneToOne
  @JoinColumn(name = "reservation_id")
  private Reservation reservation;

  private String paymentMethod;
  private int totalPrice;
  private int finalPrice;
  private String paymentStatus;
}