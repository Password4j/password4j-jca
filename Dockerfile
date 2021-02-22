FROM maven:3.5.4-jdk-9-slim

WORKDIR /build
ADD pom.xml /build/pom.xml
RUN mvn verify clean --fail-never

COPY . /build
RUN mvn clean test