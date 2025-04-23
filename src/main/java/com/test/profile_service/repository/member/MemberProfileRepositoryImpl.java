package com.test.profile_service.repository.member;

import com.querydsl.core.BooleanBuilder;
import com.test.profile_service.domain.memberProfile.MemberProfile;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.test.profile_service.domain.memberProfile.QMemberProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.types.OrderSpecifier;

import java.util.List;

@RequiredArgsConstructor
public class MemberProfileRepositoryImpl implements MemberProfileRepositoryCustom{
    //QueryDSL 코드 작성
    private final JPAQueryFactory queryFactory;

    /*
    public MemberProfileRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
    */

    @Override
    public Page<MemberProfile> searchProfiles(String keyword, String sort, Pageable pageable) {
        QMemberProfile memberProfile = QMemberProfile.memberProfile;

        //쿼리에 조건을 추가하기 위한 빌더 객체를 생성
        BooleanBuilder where = new BooleanBuilder();
        if (keyword != null && !keyword.isBlank()) {
            //keyword 변수가 null이 아니고 비어 있지 않은 경우에만 쿼리 조건을 추가
            where.and(memberProfile.name.containsIgnoreCase(keyword));
        }

        OrderSpecifier<?> orderBy = getOrderSpecifier(sort, memberProfile);

        List<MemberProfile> results  = queryFactory
                .selectFrom(memberProfile)
                .where(where)
                .orderBy(orderBy)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory
                .select(memberProfile.count())
                .from(memberProfile)
                .where(where)
                .fetchOne(); //fetchOne()은 null이 될 수 있으므로 항상 null 체크 필요!

        return new PageImpl<>(results, pageable, count != null ? count : 0);
    }

    private OrderSpecifier<?> getOrderSpecifier(String sort, QMemberProfile profile) {
        return switch (sort.toUpperCase()) {
            case "NAME_ASC"       -> profile.name.asc();
            case "NAME_DESC"      -> profile.name.desc();
            case "VIEW_ASC"       -> profile.viewCount.asc();
            case "VIEW_DESC"      -> profile.viewCount.desc();
            case "REGISTER_ASC"   -> profile.registeredAt.asc();
            case "REGISTER_DESC"  -> profile.registeredAt.desc();
            default               -> profile.name.asc(); // 기본 정렬
        };
    }
}
