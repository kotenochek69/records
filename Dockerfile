FROM openjdk:17.0.1-jdk-slim as cache
FROM gradle:8.2.1 as cache
# Установите рабочую директорию
WORKDIR /app

COPY . .
RUN gradle --parallel bootJar


# Скопируйте JAR файл вашего приложения в контейнер
RUN cp build/libs/*SNAPSHOT.jar records.jar

# Определите порт, который приложение будет использовать
EXPOSE 8080
