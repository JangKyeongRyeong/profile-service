package com.test.profile_service.service.memberProfile;

import com.test.profile_service.dto.Member.MemberProfileDto;
import com.test.profile_service.dto.Member.request.MemberCreateRequest;
import com.test.profile_service.dto.Member.response.MemberProfileResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MemberProfileService {

    // 회원 생성
    MemberProfileResponse createMember(MemberCreateRequest request);

    // 회원 프로필 리스트 조회
    Page<MemberProfileResponse> getMemberList(String keyword, String sort, int page, int size);

    // 회원 프로필 상세 조회
    MemberProfileResponse getMemberProfile(Long id) throws IllegalAccessException;

    // 회원 프로필 뷰 증가
    void increaseViewCount(Long id);

}
