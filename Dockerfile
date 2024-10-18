# Usa una imagen base de OpenJDK para compilar y ejecutar la aplicación
FROM openjdk:21-jdk-slim as build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo .jar generado en el directorio de destino
COPY target/backendFrutas-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que tu aplicación Spring Boot estará escuchando
EXPOSE 8987

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]

#FROM openjdk:17-jdk-alpine
