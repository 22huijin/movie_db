package com.example.demo.user.domain;

import com.example.demo.reservation.domain.Reservation;
import com.example.demo.reservation.domain.SeatLock;
import com.example.demo.movie.domain.Review;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

  @Column(unique = true)
  private String phoneNumber;

  private String membershipType;

  @OneToMany(mappedBy = "user")
  private List<Reservation> reservations;

  @OneToMany(mappedBy = "user")
  private List<SeatLock> seatLocks;

  @OneToMany(mappedBy = "user")
  private List<Review> reviews;

  @OneToMany(mappedBy = "user")
  private List<CouponUser> couponUsers;
}