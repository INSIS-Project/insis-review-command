FROM maven:3.9.0-eclipse-temurin-17-alpine AS maven-build
WORKDIR /usr/src/review-command
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine AS app-runtime
WORKDIR /app
COPY --from=maven-build /usr/src/review-command/target/*.jar ./review-command.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "review-command.jar"]