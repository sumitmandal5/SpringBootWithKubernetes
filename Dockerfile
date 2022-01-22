FROM openjdk:11
EXPOSE 8080
WORKDIR /opt/app
COPY  target/assignment-0.0.1-SNAPSHOT.jar application.jar
ENTRYPOINT ["java","-jar","application.jar"]