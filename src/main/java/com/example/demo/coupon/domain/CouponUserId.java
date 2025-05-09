package com.example.demo.coupon.domain;

import java.io.Serializable;
import java.util.Objects;

//CouponUser 기본키 복합키용
public class CouponUserId implements Serializable {
  private Long coupon;
  private Long user;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CouponUserId that)) return false;
    return Objects.equals(coupon, that.coupon) && Objects.equals(user, that.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(coupon, user);
  }
}
