# 1.📌 프로젝트 개요
회원 프로필 조회 및 포인트 결제 시스템
Spring Boot 기반 백엔드와 React 프론트엔드로 구성된 웹 애플리케이션입니다.
회원의 프로필을 조회하고, 토스페이먼츠(Toss Payments)를 이용한 포인트 충전이 가능합니다.

# 2.⚙️ 기술 스택
Backend: Java 17, Spring Boot, JPA, QueryDSL, MySQL

Frontend: React, TypeScript

결제 API: Toss Payments

DevOps: Docker, Docker Compose, GitHub Actions (배포 예정)

# 3.🛠 주요 기능
회원 프로필 목록/상세 조회 (조회수 업데이트 포함)

프로필 정렬 및 페이지네이션 (이름순, 조회순, 등록순)

Toss Payments 기반 포인트 충전

쿠폰 할인 기능 (선택적)
Docker Compose를 통한 전체 시스템 실행

# 4.🚀 실행 방법 (로컬)
docker에 빌드
docker build -t profile-service .
docker run -d -p 8080:8080 --name profile-service profile-service

경로: profile-service\profile-fronted 에서
npm start


# 5.🔐 사용된 외부 라이브러리
Toss Payments SDK (결제 위젯 렌더링)

Axios, React Router, Tailwind (프론트 UI)

Spring Boot Starter, QueryDSL, Lombok (백엔드)

# 6.👉프로젝트 하면서 블로그에 정리
https://programmerjkr.tistory.com/category/Project/profile-service

# 7.📄 참고 문서
Toss Payments 공식 문서 (https://docs.tosspayments.com/guides/v2/payment-widget/integration)
