# Use an official OpenJDK image as the base image
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

EXPOSE 3004

# Copy the built JAR file from the previous stage to the container
COPY target/server-0.0.1-SNAPSHOT.jar app-1.0.0.jar

# Set the command to run the application
CMD ["java", "-jar", "app-1.0.0.jar"]
