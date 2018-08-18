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


### Generator API

Build 

```
$ mvn clean install
```

Execute 

```
$ java -jar services/generator-api/target/generator-api-99-thorntail.jar
```

Then go to [http://localhost:8084/generator-api/api/numbers/book]()

```
$ mvn docker:build
```

## Clients
