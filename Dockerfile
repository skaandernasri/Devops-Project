# Utiliser l'image de base OpenJDK 17
FROM openjdk:17-alpine

# Créer un volume pour les logs (optionnel)
VOLUME /tmp

# Copier le fichier JAR dans l'image Docker
COPY target/kaddem-0.0.1-SNAPSHOT.jar /app/kaddem.jar

# Exposer le port utilisé par l'application
EXPOSE 8082

# Démarrer l'application
ENTRYPOINT ["java", "-jar", "/app/kaddem.jar"]