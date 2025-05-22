package com.example.demo.membership.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PaymentConfirmedEvent {
    private final Long userId;
}
