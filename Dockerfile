# Fase 1: Build
FROM maven:3.8.1-openjdk-17 AS build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo pom.xml y descarga las dependencias de Maven
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copia el resto del código fuente de la aplicación y compílala
COPY src ./src
RUN mvn clean package -DskipTests

# Fase 2: Run
FROM openjdk:17-jdk-alpine

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado en la fase anterior al nuevo contenedor
COPY --from=build /app/target/api-microservicio3-0.0.1-SNAPSHOT.jar ./app.jar

# Expone el puerto que usa la aplicación
EXPOSE 8003

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]