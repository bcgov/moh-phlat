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
FROM maven:3.9-eclipse-temurin-17-alpine AS build

WORKDIR /app

# Build dependency offline to streamline build
COPY pom.xml .
#RUN mvn dependency:go-offline

COPY src/ ./src/
COPY ext-libs ./ext-libs

ARG PLR_TLS_TRUST_CERT
#RUN echo "Value of PLR_TLS_TRUST_CERT is: $PLR_TLS_TRUST_CERT"
#cert is base64 encoded. Decode it before writing to file
RUN echo $PLR_TLS_TRUST_CERT | base64 -d > ./src/main/resources/plr_tls_trust.cert
RUN chmod 640 ./src/main/resources/plr_tls_trust.cert

ARG ADDRESS_DOCTOR_KEYSTORE
#RUN echo "Value of ADDRESS_DOCTOR_KEYSTORE is: $ADDRESS_DOCTOR_KEYSTORE"
#keystore is base64 encoded. Decode it before writing to file
RUN echo $ADDRESS_DOCTOR_KEYSTORE | base64 -d > ./src/main/resources/addressdoctor_keystore.jks
RUN chmod 640 ./src/main/resources/addressdoctor_keystore.jks

ARG ADDRESS_DOCTOR_TRUSTSTORE
#RUN echo "Value of ADDRESS_DOCTOR_TRUSTSTORE is: $ADDRESS_DOCTOR_TRUSTSTORE"
#keystore is base64 encoded. Decode it before writing to file
RUN echo $ADDRESS_DOCTOR_TRUSTSTORE | base64 -d > ./src/main/resources/addressdoctor_truststore.jks
RUN chmod 640 ./src/main/resources/addressdoctor_truststore.jks

RUN mvn package
# Rename jar file to have  predictable name
RUN mv target/*.jar target/phlat-backend.jar

FROM eclipse-temurin:17.0.20.1_1-jdk-alpine-3.24
COPY --from=build app/target/phlat-backend.jar  /app/target/phlat-backend.jar

# Install the tzdata
RUN apk update && apk upgrade && apk add --no-cache tzdata && apk info tzdata
ENV TZ=America/Vancouver

EXPOSE 8088
ENTRYPOINT [ "java", "-XX:MaxRAMPercentage=80.0", "-jar", "/app/target/phlat-backend.jar" ]
