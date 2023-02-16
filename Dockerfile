FROM openjdk:20-ea-19-jdk-slim
COPY ./target/challenge-0.0.1-SNAPSHOT.jar challenge.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","challenge.jar"]