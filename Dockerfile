# Base image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy JAR file
COPY target/spring-boot-crud-0.0.1-SNAPSHOT.jar app.jar

# Run application
CMD ["java", "-jar", "app.jar"]

# Expose port
EXPOSE 8080