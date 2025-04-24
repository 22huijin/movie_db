package com.example.demo.payment.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PRICINGPOLICY")
@Getter
@Setter
public class PricingPolicy {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long priceId;

  private String dayType;
  private String timeRange;
  private String userType;

  private Integer price;
}