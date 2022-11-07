FROM openjdk:11
ADD target/rmdemo.jar rmdemo.jar
ENTRYPOINT ["java", "-jar","rmdemo.jar"]