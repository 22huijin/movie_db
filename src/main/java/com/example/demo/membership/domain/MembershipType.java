package com.example.demo.membership.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MEMBERSHIP_TYPE")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MembershipType {

  @Id
  @Column(name = "membership_type_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long membershipTypeId;

  @Column(name = "membership_name", nullable = false, length = 100)
  private String membershipName;

  @Column(name = "benefit_description", length = 2000)
  private String benefitDescription;

  @Column(name = "upgrade_condition", nullable = false, length = 2000)
  private String upgradeCondition;
}