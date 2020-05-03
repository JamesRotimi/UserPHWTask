FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/
ENTRYPOINT ["java","-jar","/app.jar"]