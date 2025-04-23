package com.test.profile_service.repository.member;

import com.test.profile_service.domain.memberProfile.MemberProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberProfileRepositoryCustom {

    Page<MemberProfile> searchProfiles(String keyword, String sortType, Pageable pageable);

}
