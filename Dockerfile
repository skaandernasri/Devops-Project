FROM openjdk:17-jdk-alpine AS downloader

RUN apk update && apk add --no-cache curl

ENV JAR_URL=http://192.168.50.4:8081/repository/maven-repo/tn/esprit/spring/kaddem/0.0.1-SNAPSHOT/kaddem-0.0.1-20241103.215339-1.jar

RUN curl -u admin:abcabc123 -o /kaddem-0.0.1-20241103.215339-1.jar $JAR_URL

FROM openjdk:17-jdk-alpine
EXPOSE 8089

COPY --from=downloader /kaddem-0.0.1-20241103.215339-1.jar kaddem-0.0.1-20241103.215339-1.jar

ENTRYPOINT ["java", "-jar", "/kaddem-0.0.1-20241103.215339-1.jar"]