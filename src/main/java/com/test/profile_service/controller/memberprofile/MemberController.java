package com.test.profile_service.controller.memberprofile;

import com.test.profile_service.dto.Member.request.MemberCreateRequest;
import com.test.profile_service.dto.Member.response.MemberProfileResponse;
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

    @PostMapping("/members")
    public ResponseEntity<MemberProfileResponse> createMember(@RequestBody @Valid MemberCreateRequest request) {
        MemberProfileResponse response = memberProfileService.createMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/members")
    public Page<MemberProfileResponse> getMembers(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "NAME_ASC") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
      return memberProfileService.getMemberList(keyword, sort, page, size);
    }

    @PutMapping("/members/{id}/view")
    public ResponseEntity<Void> increamentViewCount (@PathVariable Long id) {
        memberProfileService.increaseViewCount(id);
        return ResponseEntity.ok().build();
    }
}
