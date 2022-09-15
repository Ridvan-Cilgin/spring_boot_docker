FROM openjdk:8-jdk-alpine
EXPOSE 8080
COPY target/spring_boot_docker-0.0.1-SNAPSHOT.jar spring_boot_docker.jar
ENTRYPOINT ["java","-jar","/spring_boot_docker.jar"]