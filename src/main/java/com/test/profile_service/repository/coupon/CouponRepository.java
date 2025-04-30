package com.test.profile_service.repository.coupon;

import com.test.profile_service.domain.CouponCode;
import com.test.profile_service.domain.coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByCode(CouponCode code);
}
