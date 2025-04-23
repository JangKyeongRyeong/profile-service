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

    public MemberProfile(String name) {
        this.name = name;
    }

    public void increaseViewCount() {
        this.viewCount += 1;
    }

    @PrePersist
    protected void onCreate() {
        this.registeredAt = LocalDateTime.now();
        this.viewCount = 0;
    }
}
