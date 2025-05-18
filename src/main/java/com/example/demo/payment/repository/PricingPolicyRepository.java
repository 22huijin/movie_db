package com.example.demo.payment.repository;

import com.example.demo.payment.domain.PricingPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PricingPolicyRepository extends JpaRepository<PricingPolicy, Long> {
  Optional<PricingPolicy> findByDayTypeAndTimeRangeAndUserType(
      String dayType, String timeRange, String userType
  );
}