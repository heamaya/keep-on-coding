FROM openjdk:8

RUN mkdir /data

COPY target/bookshelf-rest-api-1.0-SNAPSHOT.jar /data/bookshelf-rest-api-1.0-SNAPSHOT.jar

COPY config.yml /data/config.yml

CMD java -jar /data/bookshelf-rest-api-1.0-SNAPSHOT.jar server /data/config.yml

EXPOSE 8080
