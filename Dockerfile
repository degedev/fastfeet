FROM gradle:jdk21-alpine AS build
WORKDIR /app
COPY . ./
RUN gradle build -x test --no-daemon

FROM eclipse-temurin:21-jre-alpine AS runtime
WORKDIR /app
COPY --from=build /app/build/libs/fastfeet-0.0.1-SNAPSHOT.jar ./app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
