FROM maven:3.6.3-openjdk-17-slim

MAINTAINER Maciej Koman "maciekkoman@gmail.com"

RUN apt-get update && \
    apt-get upgrade && \
    apt-get install -y git && \
    git clone https://github.com/koman-maciej/beer-lovers.git && \
    cd /beer-lovers && \
    mvn install

EXPOSE 8080
WORKDIR /beer-lovers