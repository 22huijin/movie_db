package com.example.demo.payment.service;

import com.example.demo.coupon.domain.CouponUser;
import com.example.demo.coupon.repository.CouponUserRepository;
import com.example.demo.payment.domain.Payment;
import com.example.demo.payment.domain.PricingPolicy;
import com.example.demo.payment.dto.PaymentRequestDTO;
import com.example.demo.payment.dto.PaymentResponseDTO;
import com.example.demo.payment.repository.PaymentRepository;
import com.example.demo.reservation.domain.Reservation;
import com.example.demo.reservation.domain.SeatLock;
import com.example.demo.reservation.repository.ReservationRepository;
import com.example.demo.reservation.repository.SeatLockRepository;
import com.example.demo.schedule.domain.ScheduleSeat;
import com.example.demo.schedule.repository.ScheduleSeatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

  private final SeatLockRepository seatLockRepository;
  private final ScheduleSeatRepository scheduleSeatRepository;
  private final ReservationRepository reservationRepository;
  private final CouponUserRepository couponUserRepository;
  private final PaymentRepository paymentRepository;

  @Transactional
  public PaymentResponseDTO processPayment(PaymentRequestDTO request) {
    LocalDateTime now = LocalDateTime.now();

    // 1. seatLock 조회
    List<SeatLock> seatLocks = seatLockRepository.findAllById(
        request.getDetails().stream().map(PaymentRequestDTO.PaymentDetail::getLockId).toList()
    );

    if (seatLocks.size() != request.getDetails().size()) {
      return new PaymentResponseDTO(false, "유효하지 않은 좌석 잠금 정보가 있습니다.");
    }

    // 2. 만료 여부 확인
    for (SeatLock lock : seatLocks) {
      if (lock.getExpiresAt().isBefore(now)) {
        return new PaymentResponseDTO(false, "좌석 예매 가능 시간이 지났습니다. 좌석을 다시 선택해 주세요.");
      }
    }

    // 3. 좌석 상태 CONFIRMED 처리
    for (SeatLock lock : seatLocks) {
      ScheduleSeat ss = lock.getScheduleSeat();
      ss.setStatus("CONFIRMED");
      scheduleSeatRepository.save(ss);
    }

    // 4. 결제 저장
    for (PaymentRequestDTO.PaymentDetail detail : request.getDetails()) {
      SeatLock lock = seatLocks.stream()
          .filter(l -> l.getLockId().equals(detail.getLockId()))
          .findFirst()
          .orElseThrow();

      ScheduleSeat ss = lock.getScheduleSeat();
      Reservation reservation = reservationRepository.findByScheduleSeat(ss)
          .orElseThrow(() -> new IllegalStateException("예약 정보가 없습니다."));
      reservation.setStatus("CONFIRMED");

      PricingPolicy pricingPolicy = reservation.getPricingPolicy();
      int price = pricingPolicy.getPrice();

      Integer finalPrice = price;
      CouponUser couponUser = null;

      if (detail.getCouponUserId() != null) {
        couponUser = couponUserRepository.findById(detail.getCouponUserId())
            .orElseThrow(() -> new IllegalArgumentException("쿠폰 정보가 없습니다."));
        finalPrice = price - couponUser.getCoupon().getDiscountAmount().intValue();
      }

      Payment payment = new Payment();
      payment.setReservation(reservation);
      payment.setPaymentMethod(request.getPaymentMethod());
      payment.setCouponUser(couponUser);
      payment.setFinalPrice(finalPrice);
      payment.setPaymentStatus("PENDING");

      paymentRepository.save(payment);
    }

    seatLockRepository.deleteAll(seatLocks);

    return new PaymentResponseDTO(true, "결제가 완료되었습니다.");
  }
}