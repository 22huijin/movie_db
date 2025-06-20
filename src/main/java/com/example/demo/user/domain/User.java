package com.example.demo.user.domain;

import com.example.demo.coupon.domain.CouponUser;
import com.example.demo.reservation.domain.Reservation;
import com.example.demo.reservation.domain.SeatLock;
import com.example.demo.movie.domain.Review;
import com.example.demo.membership.domain.MembershipType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq_gen")
  @SequenceGenerator(name = "users_seq_gen", sequenceName = "USERS_SEQ", allocationSize = 1)
  private Long userId;

  private String nickname;

  @Column(unique = true)
  private String email;

  private String password;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "membership_type_id")
  private MembershipType membershipType;

  private String role;

  private LocalDate birthDate;     // 생일

  private LocalDate joinDate;   // 가입일

  private String withdrawStatus = "active";  // 탈퇴 여부

  private Integer availablePoint;

  @OneToMany(mappedBy = "user")
  private List<Reservation> reservations;

  @OneToMany(mappedBy = "user")
  private List<SeatLock> seatLocks;

  @OneToMany(mappedBy = "user")
  private List<Review> reviews;

  @OneToMany(mappedBy = "user")
  private List<CouponUser> couponUsers;
}
