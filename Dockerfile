FROM amazoncorretto:19-alpine-jdk
MAINTAINER LEMMA
COPY target/challenge-0.0.1-SNAPSHOT.jar avorischallenge.jar
ENTRYPOINT ["jave","-jar","/avorischallenge.jar"]