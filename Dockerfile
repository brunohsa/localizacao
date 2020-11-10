# Dockerfile
FROM openjdk:11-jre

EXPOSE 8086

ADD autenticacao-service/target/localizacao-1.0-SNAPSHOT.jar app/localizacao.jar
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=docker", "app/localizacao.jar"]