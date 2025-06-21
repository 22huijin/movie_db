package com.example.demo.membership.controller;

import com.example.demo.membership.domain.MembershipType;
import com.example.demo.membership.dto.MembershipTypeRequestDTO;
import com.example.demo.membership.service.MembershipTypeRegisterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/memberships")
@Tag(name = "멤버십", description = "멤버십 등록 api")
public class MembershipTypeController {

    private final MembershipTypeRegisterService membershipTypeService;

    public MembershipTypeController(MembershipTypeRegisterService membershipTypeService) {
        this.membershipTypeService = membershipTypeService;
    }

    @PostMapping("/register")
    @Operation(summary = "멤버십 등록", description = "멤버십을 등록합니다.")
    public MembershipType register(@RequestBody MembershipTypeRequestDTO dto) {
        return membershipTypeService.registerMembershipType(dto);
    }
}
