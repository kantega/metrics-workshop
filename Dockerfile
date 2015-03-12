FROM maven:3-jdk-8

EXPOSE 8080

ADD . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean install -e

WORKDIR /usr/src/app/webapp
CMD mvn jetty:run

