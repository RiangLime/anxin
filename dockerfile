FROM openjdk:17-jdk-slim
EXPOSE 8093
ARG JAR_FILE
ADD target/${JAR_FILE} /app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]
