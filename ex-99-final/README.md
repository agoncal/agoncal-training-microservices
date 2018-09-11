# Demo 99

Final demo with the entire application up and running

### Cleaning Docker images

If you need to clean all the Docker images, use the following commands:

* `docker image ls | grep bookstore`
* `/bin/bash -c 'docker image rm $(docker image ls -q "bookstore/*") -f'`


## Infrastructure

The microservice architecture needs a few tools running before it gets to work.

### Consul

Consul is used to register and discover all the microservices

```
$ docker-compose -f consul/consul.yml up
```

Then go to the web interface at http://localhost:8500

## Microservices

All microservices can get built and executed with the following commands:

To build: 

```
$ mvn clean install
```

To execute you can either use the Spring Boot maven plugin or just execute the Jar:

```
$ mvn spring-boot:run
```

```
$ mvn clean install
$ java -jar services/generator-api/target/xxx-api-xx-thorntail.jar
```

To build a Docker image out of a microservice

```
$ mvn docker:build
```

### Generator API

The Generator API generates book numbers. Check the following URLs:

* API at [http://localhost:8081/generator/api/numbers]()
* Swagger UI at [http://localhost:8081/generator/swagger-ui.html]()
* Swagger contract at [http://localhost:8081/generator/v2/api-docs]()

### Inventory API

The Inventory API manages the number of books available in the warehouses. Check the following URLs:

* API at [http://localhost:8082/inventory/api/books]()
* Swagger UI at [http://localhost:8082/inventory/swagger-ui.html]()
* Swagger contract at [http://localhost:8082/inventory/v2/api-docs]()

### Top Rated API

The Top Rated API manages the top rated books. Check the following URLs:

* API at [http://localhost:8084/toprated/api/books]()
* Swagger UI at [http://localhost:8084/toprated/swagger-ui.html]()
* Swagger contract at [http://localhost:8084/toprated/v2/api-docs]()

## Databases

Drop database

```
$ mvn liquibase:dropAll
```

Liquibase

```
$ mvn clean compile
$ mvn liquibase:update
```

## Clients
