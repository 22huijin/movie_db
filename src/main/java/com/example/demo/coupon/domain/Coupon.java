package com.example.demo.coupon.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
public class Coupon {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coupon_seq_gen")
  @SequenceGenerator(name = "coupon_seq_gen", sequenceName = "COUPON_SEQ", allocationSize = 1)
  private Long couponId;

  private String couponName;
  private BigDecimal discountAmount;

  @OneToMany(mappedBy = "coupon")
  private List<CouponUser> couponUsers;
}