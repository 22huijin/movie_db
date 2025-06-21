package com.example.demo.membership.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MembershipTypeRequestDTO {
    private String membershipName;
    private String benefitDescription;
    private String upgradeCondition;
    private BigDecimal pointAccumulationRate;
}

