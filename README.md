# Beer lovers
It's very beginning of Haufe's challenge. Please, read this README to get more information

## Prerequisites
- [Maven](https://maven.apache.org/) at least version 3 installed
- [JDK](https://www.oracle.com/java/technologies/javase-downloads.html) at least version 8 installed
- [Docker](https://docs.docker.com/engine/install/) installed

## Get started
### How to build & run project
  - Clone this project into your machine and go to the project folder
  - Configure maven wrapper `mvn -N io.takari:maven:wrapper`
  - Build `./mvnw install`
  - Run
    1. `./mvnw spring-boot:run`
    2. Go to your web browser and go to the address: `http://localhost:8080`

### How to build & run docker image
  - Clone this project into your machine and go to the project folder
  - Build `docker build -t beer-lovers:0.0.1 .`
  - Run
    1. `docker run -d -p <port>:8080 beer-lovers:0.0.1 mvn spring-boot:run`
    2. Go to your web browser and go to the address: `http://localhost:<port>`
### API Documentation
   1. Run project (for more info, please read **How to build & run ...**)
   2. Go to the address: `http://localhost:<port>/swagger-ui/`
