package com.test.profile_service.domain.memberProfile;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MemberProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int viewCount;
    private LocalDateTime registeredAt;

    private Integer points; // 보유 포인트 (원화 1:1 매칭)

    public MemberProfile(String name) {
        this.name = name;
        this.viewCount = 0;
    }

    public void increaseViewCount() {
        this.viewCount ++;
    }

    // 포인트 적립
    public  void addPoints(int amount) {
        this.points += amount;
    }

    // 포인트 사용(차감)
    public void usePoints(int amount) {
        if(this.points < amount) throw new IllegalStateException("포인트가 부족합니다.");
        this.points -= amount;
    }

    @PrePersist
    protected void onCreate() {
        this.registeredAt = LocalDateTime.now();
        this.viewCount = 0;
    }
}
