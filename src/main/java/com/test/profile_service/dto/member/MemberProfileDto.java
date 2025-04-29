package com.test.profile_service.dto.member;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberProfileDto {
    private Long id;
    private String name;
    private int viewCount;
    private LocalDateTime registeredAt;
}