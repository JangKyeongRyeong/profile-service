package com.test.profile_service.repository.member;

import com.test.profile_service.domain.memberProfile.MemberProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberProfileRepository extends JpaRepository<MemberProfile, Long>, MemberProfileRepositoryCustom {
    // JpaRepository + Custom 기능 같이 쓸 수 있음
}
