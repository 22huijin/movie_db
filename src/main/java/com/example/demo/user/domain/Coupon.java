package com.example.demo.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Coupon {
  @Id
  @GeneratedValue
  private Long couponId;

  private String couponName;
  private int discountAmount;

  @OneToMany(mappedBy = "coupon")
  private List<CouponUser> couponUsers;
}