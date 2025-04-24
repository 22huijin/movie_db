package com.example.demo.user.domain;

import com.example.demo.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "COUPON_USER")
@Getter
@Setter
public class CouponUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long couponUserId;

  @ManyToOne
  @JoinColumn(name = "coupon_id", nullable = false)
  private Coupon coupon;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  private LocalDate validUntil;
}