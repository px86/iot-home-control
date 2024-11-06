# Stage 1
FROM openjdk:17-alpine AS build

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x ./mvnw
RUN ./mvnw dependency:go-offline -B

COPY src/ ./src
RUN ./mvnw package -DskipTests

# Stage 2
FROM openjdk:17-alpine

WORKDIR /app
COPY --from=build /app/target/*.jar ./app.jar

#EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]