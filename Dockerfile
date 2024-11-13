FROM openjdk:17-jdk-alpine
EXPOSE 8082
COPY target/kaddem-0.0.1.jar kaddem-0.0.1.jar
ENTRYPOINT ["java","-jar","/kaddem-0.0.1.jar"]
