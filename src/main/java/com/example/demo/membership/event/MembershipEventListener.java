package com.example.demo.membership.event;

import com.example.demo.membership.event.PaymentConfirmedEvent;
import com.example.demo.membership.event.ReservationCancelledEvent;
import com.example.demo.membership.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MembershipEventListener {

    private final MembershipService membershipService;

    @EventListener
    public void handlePaymentConfirmed(PaymentConfirmedEvent event) {
        membershipService.updateMembership(event.getUserId());
    }

    @EventListener
    public void handleReservationCancelled(ReservationCancelledEvent event) {
        membershipService.updateMembership(event.getUserId());
    }
}
