FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 5432
RUN mkdir /app
COPY target/*.jar /app/UserPHWTask.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/UserPHWTask.jar"]