# Use official Maven image with JDK 25
FROM maven:3.9.9-eclipse-temurin-25 AS build

# Set working directory
WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build the project (download dependencies + compile)
RUN mvn clean package -DskipTests

# ---- Runtime image ----
FROM eclipse-temurin:25-jdk

WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Copy configuration (API key, etc.)
COPY src/main/resources/config.properties ./config.properties

# Expose no ports (CLI app, not server)
ENTRYPOINT ["java", "-jar", "app.jar"]
