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