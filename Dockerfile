FROM maven:3.9-eclipse-temurin-19 AS build
WORKDIR usr/src/app
COPY src /src
COPY pom.xml /
RUN mvn -f /pom.xml -DskipTests clean package

FROM eclipse-temurin:19
WORKDIR usr/src/app
COPY --from=build /target/*.jar review.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "review.jar"]