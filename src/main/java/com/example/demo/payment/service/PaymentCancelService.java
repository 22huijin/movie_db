package com.example.demo.payment.service;

import com.example.demo.coupon.domain.CouponUser;
import com.example.demo.payment.domain.Payment;
import com.example.demo.payment.dto.PaymentCancelRequestDto;
import com.example.demo.payment.dto.PaymentCancelResponseDto;
import com.example.demo.payment.repository.PaymentRepository;
import com.example.demo.reservation.domain.Reservation;
import com.example.demo.reservation.repository.ReservationRepository;
import com.example.demo.schedule.domain.Schedule;
import com.example.demo.schedule.domain.ScheduleSeat;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentCancelService {

  private final ReservationRepository reservationRepository;
  private final PaymentRepository paymentRepository;

  @Transactional
  public PaymentCancelResponseDto cancelPayment(PaymentCancelRequestDto request) {
    Long reservationId = request.getReservationId();

    Reservation reservation = reservationRepository.findById(reservationId)
        .orElseThrow(() -> new IllegalArgumentException("예약 정보를 찾을 수 없습니다."));

    reservation.setStatus("CANCEL");
    reservation.setUpdateTime(LocalDateTime.now());

    // 1. schedule_seat 상태를 AVAILABLE로 변경
    ScheduleSeat scheduleSeat = reservation.getScheduleSeat();
    scheduleSeat.setStatus("AVAILABLE");

    // 2. schedule.availableSeats +1 증가
    Schedule schedule = scheduleSeat.getSchedule();
    schedule.setAvailableSeats(schedule.getAvailableSeats() + 1);

    // 3. 결제 상태 및 쿠폰 처리
    Payment payment = paymentRepository.findAll().stream()
        .filter(p -> p.getReservation().getReservationId().equals(reservationId))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("결제 정보를 찾을 수 없습니다."));

    payment.setPaymentStatus("CANCEL");

    // 쿠폰 환불 처리
    CouponUser couponUser = payment.getCouponUser();
    if (couponUser != null) {
      couponUser.setStatus("UNUSED");
    }

    Integer finalPrice = payment.getFinalPrice();
    return new PaymentCancelResponseDto(true, finalPrice + "원 환불되었습니다.");
  }
}