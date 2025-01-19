FROM gradle:8-jdk17-alpine AS build
WORKDIR /app
COPY . .
RUN gradle build -x test --no-daemon --stacktrace

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]