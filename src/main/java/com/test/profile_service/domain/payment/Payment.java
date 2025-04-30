package com.test.profile_service.domain.payment;

import com.test.profile_service.domain.coupon.Coupon;
import com.test.profile_service.domain.memberProfile.MemberProfile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false) // @Column(unique = true)를 적용하여 중복 결제 시도 시 예외 발생
    private String paymentKey;      // paymentKey(Toss 결제 API에서 발급하는 고유 결제키)

    @Column(nullable = false)
    private String orderId;         // 주문번호 클라이언틍에서 생성한 고유 문자열

    @Column(nullable = false)       // 결제 금액
    private int amount;

    @Column(nullable = false)
    private String status;          // 결제 상태 ( 1. "DONE"   2. "CANCLED" )

    private String method;          // 결제 수단 ( ex. CARD , VBANK 등)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberProfile member;   //결제한 Member_id 들과 다대일 관계

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;          // 관련 쿠폰

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }
    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
