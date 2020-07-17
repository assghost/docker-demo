FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/ghost-docker-demo-1.0.0.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]