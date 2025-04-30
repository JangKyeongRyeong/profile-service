package com.test.profile_service.domain.coupon;

import com.test.profile_service.annotation.EnumValue;
import com.test.profile_service.domain.CouponCode;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    @EnumValue(enumClass = CouponCode.class, message = "유효하지 않은 쿠폰 코드입니다.", ignoreCase = true)
    private CouponCode code;            // 쿠폰 코드

    @Column(nullable = false)
    private int discountRate;          // 할인율

    @Column(nullable = false)
    private int remainingCount;         // 남은 사용 횟수

    private Integer maxDiscountAmount;  // 할인한도 5,000원

    private LocalDateTime createAt;

    public boolean isUsable() {
        return remainingCount > 0;
    }

    // 쿠폰 사용 (횟수 차감)
    public void use() {
        if(remainingCount <= 0) {
            throw new IllegalStateException("쿠폰 사용 가능 횟수를 초과하였습니다.");
        }
        remainingCount--;
    }

    // 쿠폰 적용
    public int applyDiscount(int originalAmount, int discountRate, int maxDiscountAmount) {
        int discount = originalAmount * discountRate/100;
        return originalAmount - Math.min(discount, maxDiscountAmount);
    }

    @PrePersist
    public void prePersist() {
        createAt = LocalDateTime.now();
    }

}
