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
COPY ext-libs ./ext-libs

ARG PLR_TLS_TRUST_CERT
RUN echo "Value of PLR_TLS_TRUST_CERT is: $PLR_TLS_TRUST_CERT"
#cert is base64 encoded. Decode it before writing to file
RUN echo $PLR_TLS_TRUST_CERT | base64 -d > ./src/main/resources/plr_tls_trust.cert

RUN chmod 640 ./src/main/resources/plr_tls_trust.cert

RUN mvn package
# Rename jar file to have  predictable name
RUN mv target/*.jar target/phlat-backend.jar

FROM openjdk:17-jdk
COPY --from=build app/target/phlat-backend.jar  /app/target/phlat-backend.jar

EXPOSE 8088
ENTRYPOINT [ "java", "-jar", "/app/target/phlat-backend.jar" ]
