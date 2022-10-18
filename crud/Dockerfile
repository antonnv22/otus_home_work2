FROM openjdk:11-slim

ADD build/libs/crud-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8000/tcp
ENTRYPOINT java -jar $JAVA_OPTS app.jar