# 1단계: Build Stage
FROM gradle:8.5.0-jdk17-alpine AS build
WORKDIR /app

# 필요한 파일만 복사 (의존성 캐시 활용)
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle
RUN ./gradlew dependencies

# 전체 복사 후 빌드
COPY . /app
RUN ./gradlew build -x test --stacktrace

# JAVA_HOME 환경 변수 설정
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# 2단계: Run Stage
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=build/libs/*.jar
COPY --from=build ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]