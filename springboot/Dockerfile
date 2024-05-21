FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ADD ./target/springboot-0.0.1-SNAPSHOT.jar springboot.jar
ENTRYPOINT ["java","-jar","/springboot.jar"]