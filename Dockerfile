FROM maven:3.9.5-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17

WORKDIR /app

COPY --from=build /app/target/yufaabcore-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "yufaabcore-0.0.1-SNAPSHOT.jar"]