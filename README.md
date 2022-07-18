# Spring Hospital System

This Spring project includes three Microservices named eureka-server-module, doctor-module and hospital-module. These microservices register themselves to server-module which is a Eureka Server. Microservices communicate each other by using Feigns.

- Spring Cloud Eureka, OpenFeign, H2 Database Engine, OpenAPI 3 were used.
- CRUD operations are available for Doctor and Hospital entities.

## Run Setup
- To run project in local:
  - Import the base maven.
  - Open a terminal and type `mvn clean install`
  - After the successful build you are ready to run the microservices.

## Running the Microservices
- To run microservices correctly:
  - Run eureka-server-module first.
  - Then you can run other microservices.

## Testing the Microservices
- To test microservices:
  - Find the `documentation` folder located at the root folder of project.
  - Here you have two options:
    1. You can import POSTMAN Collection to POSTMAN and start testing.
    2. You can get SwaggerUI Path of each microservice by taking a look to `SwaggerUI-Paths.txt` file.



