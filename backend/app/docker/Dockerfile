####
# This Dockerfile is used in order to build a container that runs the Spring Boot application
#
# Build the image with:
#
# docker build -f docker/Dockerfile -t phlat/phlat-backend .
#
# Then run the container using:
#
# docker run -i --rm -p 8088:8088 phlat/phlat-backend
####
FROM quay.io/devfile/maven:3.8.1-openjdk-17-slim AS build

WORKDIR /app

# Build dependency offline to streamline build
COPY pom.xml .
#RUN mvn dependency:go-offline

COPY src/ ./src/
RUN mvn package 
# compute the created jar name and put it in a known location to copy to the next layer.
# If the user changes pom.xml to have a different version, or artifactId, this will find the jar 
#RUN grep version /build/app/target/maven-archiver/pom.properties | cut -d '=' -f2 >.env-version 
#RUN grep artifactId /build/app/target/maven-archiver/pom.properties | cut -d '=' -f2 >.env-id
#RUN mv /build/app/target/$(cat .env-id)-$(cat .env-version).jar /build/app/target/phlat-backend.jar
RUN mv target/*.jar target/phlat-backend.jar

FROM openjdk:17-jdk
COPY --from=build app/target/phlat-backend.jar  /app/target/phlat-backend.jar

EXPOSE 8088
ENTRYPOINT [ "java", "-jar", "/app/target/phlat-backend.jar" ]
