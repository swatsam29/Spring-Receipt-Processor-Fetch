# Use an official AdoptOpenJDK 17 runtime as a parent image
FROM openjdk:21

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Set the working directory to /app
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/springreceiptprocessor.jar /app/springreceiptprocessor.jar

# Run the application when the container launches
CMD ["java", "-jar", "springreceiptprocessor.jar"]
