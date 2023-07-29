# Use the desired Amazon Corretto 17 image as the base image
FROM amazoncorretto:17-al2-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot executable JAR file to the container
COPY target/djinoworld-0.0.1-SNAPSHOT.jar /app/djinoworld.jar

# Expose the port on which your Spring Boot application is running
EXPOSE 8080

# Command to run your Spring Boot application when the container starts
CMD ["java", "-jar", "djinoworld.jar"]
