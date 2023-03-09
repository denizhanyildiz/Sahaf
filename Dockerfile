FROM openjdk:17
WORKDIR /app
COPY target/Sahaf-0.0.1-SNAPSHOT.jar /app/Sahaf-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "Sahaf-0.0.1-SNAPSHOT.jar"]
