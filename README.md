📌 프로젝트 개요
회원 프로필 조회 및 포인트 결제 시스템
Spring Boot 기반 백엔드와 React 프론트엔드로 구성된 웹 애플리케이션입니다.
회원의 프로필을 조회하고, 토스페이먼츠(Toss Payments)를 이용한 포인트 충전이 가능합니다.

⚙️ 기술 스택
Backend: Java 17, Spring Boot, JPA, QueryDSL, MySQL

Frontend: React, TypeScript

결제 API: Toss Payments

DevOps: Docker, Docker Compose, GitHub Actions (배포 예정)

🛠 주요 기능
회원 프로필 목록/상세 조회 (조회수 업데이트 포함)

프로필 정렬 및 페이지네이션 (이름순, 조회순, 등록순)

Toss Payments 기반 포인트 충전

쿠폰 할인 기능 (선택적)
Docker Compose를 통한 전체 시스템 실행

🚀 실행 방법 (로컬)
# 1. 클론
git clone https://github.com/JangKyeongRyeong/profile-service.git
cd member-profile-payment

# 2. 환경 변수 설정 (.env 또는 application.yml)
# tosspayments.secretKey 등 설정 필요

# 3. Docker로 실행
docker-compose up --build

🔐 사용된 외부 라이브러리
Toss Payments SDK (결제 위젯 렌더링)

Axios, React Router, Tailwind (프론트 UI)

Spring Boot Starter, QueryDSL, Lombok (백엔드)

📄 참고 문서
Toss Payments 공식 문서 (https://docs.tosspayments.com/guides/v2/payment-widget/integration)
