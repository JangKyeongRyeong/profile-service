package com.test.profile_service.controller.memberprofile;

import com.test.profile_service.domain.memberProfile.MemberProfile;
import com.test.profile_service.dto.Member.MemberProfileDto;
import com.test.profile_service.dto.Member.request.MemberCreateRequest;
import com.test.profile_service.dto.Member.response.MemberProfileResponse;
import com.test.profile_service.service.memberProfile.MemberProfileService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    private final MemberProfileService memberProfileService;

    public MemberController(MemberProfileService memberProfileService) {
        this.memberProfileService = memberProfileService;
    }

    @PostMapping("/Member")
    public void saveMembers(@RequestBody @Valid MemberCreateRequest request) {
        memberProfileService.saveMember(request);
    }

    @GetMapping("/Member")
    public Page<MemberProfileResponse> getMembers(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "NAME_ASC") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
      return memberProfileService.getMemberList(keyword, sort, page, size);
    }
}
