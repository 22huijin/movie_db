//package com.example.demo.payment.domain;
//
//import com.example.demo.reservation.domain.Reservation;
//<<<<<<< HEAD
//import com.example.demo.coupon.domain.CouponUser;
//=======
////import com.example.demo.user.domain.CouponUser;
//>>>>>>> b2ad39f649491d177a420eaeabef62595a771b35
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Table(name = "PAYMENT")
//@Getter
//@Setter
//public class Payment {
//
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  private Long paymentId;
//
//  @OneToOne
//  @JoinColumn(name = "reservation_id", nullable = false)
//  private Reservation reservation;
//
//  private String paymentMethod;
//
//  @ManyToOne
//  @JoinColumn(name = "coupon_user_id")
//  private CouponUser couponUser;
//
//  private Integer finalPrice;
//
//  private String paymentStatus;
//}