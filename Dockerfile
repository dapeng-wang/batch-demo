FROM eclipse-temurin:19-jre

RUN mkdir "/app" && mkdir "/app/output"
COPY target/*.jar /app/app.jar
EXPOSE 8080
WORKDIR /app
ENTRYPOINT ["java","-jar","app.jar"]