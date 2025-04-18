package com.example.demo.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@IdClass(CouponUserId.class)
@Getter
@Setter
public class CouponUser implements Serializable {

  @Id
  @ManyToOne
  @JoinColumn(name = "coupon_id")
  private Coupon coupon;

  @Id
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  private LocalDate validUntil;
}