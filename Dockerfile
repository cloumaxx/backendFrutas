# Fase 1: Compilación de la aplicación usando Maven
FROM maven:3.9.4-eclipse-temurin-21 AS build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo pom.xml y descarga las dependencias
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copia el resto del código fuente y compila la aplicación
COPY src ./src
RUN mvn clean package -DskipTests

# Fase 2: Imagen ligera de OpenJDK para ejecutar la aplicación
FROM openjdk:21-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo .jar generado en la fase de build
COPY --from=build /app/target/backendFrutas-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que tu aplicación Spring Boot estará escuchando
EXPOSE 8987

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
