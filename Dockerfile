FROM openjdk:10-jdk-sid

RUN mkdir /app

WORKDIR /app

ADD build/libs/elastic-search-demo*.jar service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/urandom", "-jar", "service.jar"]