<<<<<<< HEAD
FROM openjdk:17-jdk-alpine
EXPOSE 8082
COPY target/kaddem-0.0.1.jar kaddem-0.0.1.jar
ENTRYPOINT ["java","-jar","/kaddem-0.0.1.jar"]
=======
# Use an official OpenJDK image to run Java applications
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file from the build context into the container
COPY target/kaddem-1.0.0.jar /app/app.jar

# Expose the port that your Spring Boot app will run on (8089)
EXPOSE 8089

# Run the jar file when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
>>>>>>> SayariMohamed-BI3-G2
