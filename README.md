# Quizemon
The goal of quizemon is to create a quiz game where questions are found on different geolocations. It will be built upon
open technologies as far as possible and everyone who is willing to contribute to the project is welcome to do so.
 
## Rest API's
The application is divided into two API's, one for the game and one for administration. To be able to run the API's
you need to have an ArangoDB database running on port 8529 (can be changed within the class ArangoConfiguration present
in both API projects). If you do not have an ArangoDB instance installed you can find instruction on how to install one
here: 

https://docs.arangodb.com/3.3/Manual/GettingStarted/Installing/

Another solution is to run the docker image provided here:

https://hub.docker.com/r/arangodb/arangodb/

### Game
This project is found in the folder "game-rest-api". 

To build the project run:
``` 
mvn clean install
```
To run the project run:
```
java jar target/quizemon-game-rest-api-0.0.1.beta.jar
```
You can also run the project from within your development environment by running the class "com.quizemon.Application"

#### Docs
When application is started you can find swagger documentation under:
```
http://localhost:8081/swagger-ui.html
```

### Admin
This project is found in the folder "admin-rest-api". 

To build the project run:
``` 
mvn clean install
```
To run the project run:
```
java jar target/quizemon-admin-rest-api-0.0.1.beta.jar
```
You can also run the project from within your development environment by running the class "com.quizemon.Application"

#### Docs
When application is started you can find swagger documentation under:
```
http://localhost:8080/swagger-ui.html
```