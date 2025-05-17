package com.example.demo.payment.domain;

import com.example.demo.reservation.domain.Reservation;
import com.example.demo.coupon.domain.CouponUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PAYMENT")
@Getter
@Setter
public class Payment {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_seq_gen")
  @SequenceGenerator(name = "payment_seq_gen", sequenceName = "PAYMENT_SEQ", allocationSize = 1)
  @Column(name = "payment_id")
  private Long paymentId;

  @OneToOne
  @JoinColumn(name = "reservation_id", nullable = false)
  private Reservation reservation;

  private String paymentMethod;

  @ManyToOne
  @JoinColumn(name = "coupon_user_id")
  private CouponUser couponUser;

  private Integer finalPrice;

  private String paymentStatus;
}