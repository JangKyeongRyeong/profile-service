# 1단계: Build Stage
FROM gradle:8.5.0-jdk17-alpine AS build

# 작업 디렉토리 설정
WORKDIR /app

# 의존성 파일 복사
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle
# 의존성 설치
RUN ./gradlew dependencies

# 전체 복사 후 빌드
COPY . /app
RUN ./gradlew build -x test --stacktrace

#JAVA_HOME 환경 변수 설정
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# 2단계: Run Stage
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
VOLUME /tmp

# 포트 오픈 (8080)
EXPOSE 8080

COPY --from=build /app/build/libs/app.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]