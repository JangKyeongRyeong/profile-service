package com.test.profile_service.service.memberProfile;

import com.test.profile_service.database.mapper.MemberProfileMapper;
import com.test.profile_service.domain.memberProfile.MemberProfile;
import com.test.profile_service.dto.Member.MemberProfileDto;
import com.test.profile_service.dto.Member.request.MemberCreateRequest;
import com.test.profile_service.dto.Member.response.MemberProfileResponse;
import com.test.profile_service.repository.member.MemberProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberProfileServiceImpl implements MemberProfileService {

    private final MemberProfileRepository memberProfileRepository;

    /*
    public MemberProfileServiceImpl(MemberProfileRepository memberProfileRepository) {
        this.memberProfileRepository = memberProfileRepository;
    }
    */

    @Override
    @Transactional
    public void saveMember(MemberCreateRequest request) {
        MemberProfile profile = new MemberProfile(request.getName());
        memberProfileRepository.save(profile);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<MemberProfileResponse> getMemberList(String keyword, String sort, int page, int size) {

        Pageable pageable = PageRequest.of(page,size);
        Page<MemberProfile> memberProfiles = memberProfileRepository.searchProfiles(keyword, sort, pageable);

        return memberProfiles.map(MemberProfileMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberProfileResponse getMemberProfile(Long id) throws IllegalAccessException {
        MemberProfile profile = memberProfileRepository.findById(id).orElseThrow(IllegalAccessException::new);
        return new MemberProfileResponse(profile);
    }

    @Override
    @Transactional
    public void increaseViewCount(Long id) {
        MemberProfile profile = memberProfileRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        profile.increaseViewCount();
    }
}
