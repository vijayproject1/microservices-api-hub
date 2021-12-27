FROM openjdk:14
COPY  build/libs/*-SNAPSHOT.jar  apiHub.jar
ENTRYPOINT ["java","-jar","/apiHub.jar"]