package com.test.profile_service.database.mapper;

import com.test.profile_service.domain.memberProfile.MemberProfile;
import com.test.profile_service.dto.Member.MemberProfileDto;
import com.test.profile_service.dto.Member.request.MemberCreateRequest;
import com.test.profile_service.dto.Member.response.MemberProfileResponse;

public class MemberProfileMapper {

    // Request → Entity
    public static MemberProfile toEntity(MemberCreateRequest request) {
        return new MemberProfile(request.getName());
    }

    // Entity → Dto (Service 내부 용도)
    public static MemberProfileDto toDto(MemberProfile entity) {
        return MemberProfileDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .viewCount(entity.getViewCount())
                .registeredAt(entity.getRegisteredAt())
                .build();
    }

    // Entity → Response (API 응답 용도)
    public static MemberProfileResponse toResponse(MemberProfile entity) {
        return MemberProfileResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .viewCount(entity.getViewCount())
                .registeredAt(entity.getRegisteredAt())
                .build();
    }

    // Dto → Response (가끔 유용할 수 있음)
    public static MemberProfileResponse dtoToResponse(MemberProfileDto dto) {
        return MemberProfileResponse.builder()
                .id(dto.getId())
                .name(dto.getName())
                .viewCount(dto.getViewCount())
                .registeredAt(dto.getRegisteredAt())
                .build();
    }


}
