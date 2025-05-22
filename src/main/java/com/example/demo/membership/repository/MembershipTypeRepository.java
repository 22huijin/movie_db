package com.example.demo.membership.repository;

import com.example.demo.membership.domain.MembershipType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembershipTypeRepository extends JpaRepository<MembershipType, Long> {
    Optional<MembershipType> findByMembershipName(String membershipName);
}

