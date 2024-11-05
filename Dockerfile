FROM openjdk:17-jdk-alpine AS downloader

RUN apk add --no-cache curl

# Définir l'URL du JAR dans une variable d'environnement
ENV JAR_URL=http://192.168.50.4:8081/repository/maven-repo/tn/esprit/spring/kaddem/0.0.1-SNAPSHOT/kaddem-0.0.1-20241105.154000-1.jar

# Télécharger le JAR depuis Nexus
RUN curl -u admin:admin -o /kaddem-0.0.1-20241105.154000-1.jar $JAR_URL

# Étape 2: Construire l'image finale
FROM openjdk:17-jdk-alpine
EXPOSE 8089

# Copier le JAR téléchargé depuis l'étape downloader
COPY --from=downloader /kaddem-0.0.1-20241105.154000-1.jar kaddem-0.0.1-20241105.154000-1.jar

ENTRYPOINT ["java", "-jar", "/kaddem-0.0.1-20241105.154000-1.jar"]