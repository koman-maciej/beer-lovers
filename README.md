# Beer lovers
It's very beginning of Haufe's challenge. Please, read this README to get more information

## Prerequisites
- [Maven](https://maven.apache.org/) at least version 3 installed
- [JDK](https://www.oracle.com/java/technologies/javase-downloads.html) at least version 8 installed
- [Docker](https://docs.docker.com/engine/install/) installed
- [MongoDB](https://www.mongodb.com/) launched on port `27017`

## Get started
### How to build & run project (Linux)
- Clone this project into your machine and go to the project folder
- Configure maven wrapper `mvn -N io.takari:maven:wrapper`
- Build `./mvnw install`
- Run
    1. Run MongoDb (e.g.: `docker run -d -p 27017:27017 mongo:3.6.22`)
    2. Run application: `./mvnw spring-boot:run`
    3. Go to your web browser and go to the address: `http://localhost:8080`

### How to build & run docker image
- Clone this project into your machine and go to the project folder
- Build `docker build -t beer-lovers:0.0.1 .`
- Run
    1. Run MongoDb (e.g.: `docker run -d -p 27017:27017 mongo:3.6.22`)
    2. Run application: `docker run -d --net=host beer-lovers:0.0.1 mvn spring-boot:run`
    3. Go to your web browser and go to the address: `http://localhost:8080`
    
### API Documentation
1. Run project (for more info, please read **How to build & run ...**)
2. Go to the address: `http://localhost:8080/swagger-ui/`

## Completed tasks:
- CRUD Endpoints
- Persistence and service layer
- Bonus: Endpoint pagination for `GET /v1/manufacturers`
- Bonus: Endpoint for uploading beer picture (only supports JPEG and PNG types, not bigger than 5MB) `PATCH /v1/manufacturers/{manufacturerId}/beers/{beerId}/upload`

## TODO:
- **unit tests for other possible paths that are left.** I hope the coverage for controller layer proves my skills in that area. Implementing tests with 100% coverage for the entire application is time-consuming, but important in case of real environment (and hopefully that's enough for this challenge and I deserved a beer ;) )
- **integration tests**
- **handle ISE for large files**
- **move validations to the separated classes**
