FROM openjdk:11
ADD target/rmdemo-0.0.1-SNAPSHOT.jar rmdemo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","rmdemo-0.0.1-SNAPSHOT.jar"]
