
# Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
# Click nbfs://nbhost/SystemFileSystem/Templates/Other/Dockerfile to edit this template

# Usa una imagen base con Java 22 (correcto)
FROM eclipse-temurin:22-jdk

# Crea el directorio de trabajo
WORKDIR /app

# Copia el JAR al contenedor
COPY target/banco-1.0-SNAPSHOT.jar ./app.jar

# Expón el puerto si tu app tiene una API o interfaz
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]

