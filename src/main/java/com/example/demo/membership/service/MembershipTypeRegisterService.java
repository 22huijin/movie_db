package com.example.demo.membership.service;

import com.example.demo.membership.domain.MembershipType;
import com.example.demo.membership.dto.MembershipTypeRequestDTO;
import com.example.demo.membership.repository.MembershipTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class MembershipTypeRegisterService {

    private final MembershipTypeRepository membershipTypeRepository;

    public MembershipTypeRegisterService(MembershipTypeRepository membershipTypeRepository) {
        this.membershipTypeRepository = membershipTypeRepository;
    }

    public MembershipType registerMembershipType(MembershipTypeRequestDTO dto) {
        MembershipType membershipType = new MembershipType();
        membershipType.setMembershipName(dto.getMembershipName());
        membershipType.setBenefitDescription(dto.getBenefitDescription());
        membershipType.setUpgradeCondition(dto.getUpgradeCondition());
        membershipType.setPointAccumulationRate(dto.getPointAccumulationRate());
        return membershipTypeRepository.save(membershipType);
    }
}
