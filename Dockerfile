FROM maven:3.8.5-openjdk-17 as buildStage

WORKDIR /app

COPY pom.xml .

COPY ./src ./src

RUN mvn clean install -Dmaven.test.skip=true

FROM openjdk:17.0.1-jdk-slim

WORKDIR /app

COPY --from=buildStage /app/target/shop-api-0.0.1.jar /app/shop-api.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/shop-api.jar"]