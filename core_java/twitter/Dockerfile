FROM openjdk:8-alpine
COPY target/twitter*.jar /usr/local/lib/twitter.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/twitter.jar"]