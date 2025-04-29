package com.test.profile_service.controller.memberprofile;

import com.test.profile_service.dto.member.request.MemberCreateRequest;
import com.test.profile_service.dto.member.response.MemberProfileResponse;
import com.test.profile_service.service.memberProfile.MemberProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member-profiles")
public class MemberController {

    private final MemberProfileService memberProfileService;

    // 회원 프로필 생성
    @PostMapping("/members")
    public ResponseEntity<MemberProfileResponse> createMember(@RequestBody @Valid MemberCreateRequest request) {
        MemberProfileResponse response = memberProfileService.createMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 회원 프로필 리스트 조회
    @GetMapping("/members")
    public Page<MemberProfileResponse> getMembers(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "NAME_ASC") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
      return memberProfileService.getMemberList(keyword, sort, page, size);
    }

    // 회원 상세 조회
    @GetMapping("/members/{id}")
    public ResponseEntity<MemberProfileResponse> getMemberDetail(@PathVariable Long id) throws IllegalAccessException {
        MemberProfileResponse memberProfile = memberProfileService.getMemberProfile(id);
        return ResponseEntity.ok(memberProfile);
    }

    // 회원 프로필의 조회수 증가
    @PutMapping("/members/{id}/view-count")
    public ResponseEntity<Void> increaseViewCount (@PathVariable Long id) {
        memberProfileService.increaseViewCount(id);
        return ResponseEntity.ok().build();
    }
}
