package com.example.demo.payment.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PRICINGPOLICY")
@Getter
@Setter
@NoArgsConstructor
public class PricingPolicy {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pricingpolicy_seq_gen")
  @SequenceGenerator(
      name = "pricingpolicy_seq_gen",
      sequenceName = "PRICINGPOLICY_SEQ",
      allocationSize = 1
  )
  @Column(name = "PRICE_ID")
  private Long priceId;

  @Column(name = "DAY_TYPE", nullable = false)
  private String dayType;

  @Column(name = "TIME_RANGE", nullable = false)
  private String timeRange;

  @Column(name = "USER_TYPE", nullable = false)
  private String userType;

  @Column(name = "PRICE", nullable = false)
  private Integer price;
}