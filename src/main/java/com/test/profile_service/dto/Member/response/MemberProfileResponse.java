package com.test.profile_service.dto.Member.response;

import com.test.profile_service.domain.memberProfile.MemberProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberProfileResponse {

    private long id;
    private String name;
    private int viewCount;
    private LocalDateTime registeredAt;

    public MemberProfileResponse(MemberProfile memberProfile) {
        this.id = memberProfile.getId();
        this.name = memberProfile.getName();
        this.viewCount = memberProfile.getViewCount();
    }

    @Builder
    public MemberProfileResponse(Long id, String name, int viewCount, LocalDateTime registeredAt) {
        this.id = id;
        this.name = name;
        this.viewCount = viewCount;
        this.registeredAt = registeredAt;
    }
}
