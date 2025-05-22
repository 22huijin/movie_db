package com.example.demo.payment.repository;

import com.example.demo.payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // 멤버십 승급을 위해 Payment 테이블 조회
    @Query("""
    SELECT COALESCE(SUM(p.finalPrice), 0)
    FROM Payment p
    WHERE p.reservation.user.userId = :userId
      AND p.paymentStatus = :paymentStatus
      AND p.reservation.status = :reservationStatus
      AND p.reservation.updateTime BETWEEN :startDate AND :endDate
    """)
    Long findUserConfirmedTotalBetween(@Param("userId") Long userId,
                                       @Param("paymentStatus") String paymentStatus,
                                       @Param("reservationStatus") String reservationStatus,
                                       @Param("startDate") LocalDateTime startDate,
                                       @Param("endDate") LocalDateTime endDate);

}