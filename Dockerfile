FROM openjdk:21-slim

ENV TZ=Asia/Shanghai

COPY ./target/spring-begin-0.0.1.jar /root/spring-begin-0.0.1.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=service", "-jar", "/root/spring-begin-0.0.1.jar"]