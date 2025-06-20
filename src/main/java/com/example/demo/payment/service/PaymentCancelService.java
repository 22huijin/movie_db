package com.example.demo.payment.service;

import com.example.demo.coupon.domain.CouponUser;
import com.example.demo.membership.event.ReservationCancelledEvent;
import com.example.demo.membership.domain.MembershipType;
import com.example.demo.payment.domain.Payment;
import com.example.demo.payment.dto.PaymentCancelRequestDto;
import com.example.demo.payment.dto.PaymentCancelResponseDto;
import com.example.demo.payment.repository.PaymentRepository;
import com.example.demo.reservation.domain.Reservation;
import com.example.demo.reservation.repository.ReservationRepository;
import com.example.demo.schedule.domain.Schedule;
import com.example.demo.schedule.domain.ScheduleSeat;
import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentCancelService {

  private final ReservationRepository reservationRepository;
  private final PaymentRepository paymentRepository;
  private final UserRepository userRepository;
  private final ApplicationEventPublisher eventPublisher;

  @Transactional
  public PaymentCancelResponseDto cancelPayment(PaymentCancelRequestDto request) {
    Long reservationId = request.getReservationId();

    Reservation reservation = reservationRepository.findById(reservationId)
        .orElseThrow(() -> new IllegalArgumentException("예약 정보를 찾을 수 없습니다."));
    if ("CANCEL".equalsIgnoreCase(reservation.getStatus())) {
      return new PaymentCancelResponseDto(false, "이미 결제 취소가 처리되었습니다.");
    }
    if ("PROCESSING".equalsIgnoreCase(reservation.getStatus())) {
      return new PaymentCancelResponseDto(false, "아직 결제가 처리되지 않았습니다.");
    }

    ScheduleSeat scheduleSeat = reservation.getScheduleSeat();
    Schedule schedule = scheduleSeat.getSchedule();

    // 상영 시작 시간 체크: 이미 시작된 경우 취소 불가
    if (!schedule.getStartTime().isAfter(LocalDateTime.now())) {
      return new PaymentCancelResponseDto(false, "상영 시간이 지나 취소할 수 없습니다.");
    }

    // 예약 상태 변경
    reservation.setStatus("CANCEL");
    reservation.setUpdateTime(LocalDateTime.now());

    // 좌석 상태 복구
    scheduleSeat.setStatus("AVAILABLE");

    // 남은 좌석 수 증가
    schedule.setAvailableSeats(schedule.getAvailableSeats() + 1);

    // 결제 상태 및 쿠폰 상태 변경
    Payment payment = paymentRepository.findByReservation_ReservationId(reservationId)
        .orElseThrow(() -> new IllegalArgumentException("결제 정보를 찾을 수 없습니다."));

    payment.setPaymentStatus("CANCEL");

    // 쿠폰 환불 처리
    CouponUser couponUser = payment.getCouponUser();
    if (couponUser != null) {
      couponUser.setUsageStatus("UNUSED");
    }

    /* ---------- 포인트 환불·회수 로직 ---------- */
    // 해당 유저를 락 걸어 조회
    User user = userRepository.findByIdForUpdate(reservation.getUser().getUserId())
        .orElseThrow(() -> new IllegalStateException("유저 정보가 없습니다."));

    int usedPoint     = payment.getUsedPoint() == null ? 0 : payment.getUsedPoint();
    int finalPrice    = payment.getFinalPrice();
    // 결제시에 적립됐던 포인트 = finalPrice × 적립률
    MembershipType mt = user.getMembershipType();
    BigDecimal rate   = (mt != null && mt.getPointAccumulationRate() != null)
        ? mt.getPointAccumulationRate()
        : BigDecimal.ZERO;
    int earnedPoint   = BigDecimal.valueOf(finalPrice)
        .multiply(rate)
        .setScale(0, RoundingMode.HALF_UP)
        .intValue();

    // 포인트 가감
    int newPoint = user.getAvailablePoint() + usedPoint - earnedPoint;

    int refundAmount = finalPrice;
    if (newPoint < 0) {
      // 마이너스 만큼 환불금에서 차감
      refundAmount += newPoint;  // newPoint 는 음수 → 실제 차감
      newPoint = 0;
    }
    user.setAvailablePoint(newPoint);

    // 6. 이벤트 발행
    eventPublisher.publishEvent(
        new ReservationCancelledEvent(reservation.getUser().getUserId())
    );

    // 7. 응답 반환
    return new PaymentCancelResponseDto(true, refundAmount + "원 환불되었습니다.");
  }
}