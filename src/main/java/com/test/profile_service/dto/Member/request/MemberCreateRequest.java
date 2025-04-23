package com.test.profile_service.dto.Member.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberCreateRequest {

    private long id;
    private String name;
    private int viewCount;

}
