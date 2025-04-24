package com.example.demo.payment.repository;

import com.example.demo.payment.domain.PricingPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricingPolicyRepository extends JpaRepository<PricingPolicy, Long> {
}