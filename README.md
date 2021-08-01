# Journey from Rest to Microservice

### Develop ACME Vacation Restful API Applications
   - All CRUD Operations of Vacations GET,POST,PUT,DELETE
   - Vacations should have Reviews as OneToMany
   - Use InMemory H2 DB
   - Implement all applicable HttpStatus/Error codes
   - Handling Exceptions
### API Gateway Service
   - Implement OAUTH2 based Authorization or Authentication using GitHub
   - Using ZuulProxy, Route the requests to Vacation services
   - Add some useful filters
### Consume Booking service
   - Consuming other restful service (booking service) and error handling.
   - Use Rest Template and Improvise/replace with Feign Client
   - Add Circuit breaker using Netflix Hystrix API.
### Discovery Server
   - Add Eureka naming server
   - Register Vacation and Booking services as Eureka clients using Feign
   - Load Balancing using Ribbon
### Distributed Tracing
   - zipkin installation
   - AMAQ Protocol- Kafka/Rabbit MQ/Active MQ installation
   - zipkin web interface (UI)
   - Enable dependency
### Access Application
```
Useful urls for reference
```
- API Gateways(Zuul) - http://localhost:8090/
- ACME Vacations     - http://localhost:8080/vacations
- ACME Booking feign - http://localhost:8080/vacations/1/book
- Booking service    - http://localhost:8091/hoteldata
- ACME Booking rest  - http://localhost:8080/vacations/1/book/rest
- ACME Vacation Review- http://localhost:8080/vacations/1/reviews
- Swagger API DOCS   - http://localhost:8080/swagger-ui.html
- H2 console         - http://localhost:8080/h2-console

```
References
```
- https://developer.spotify.com/documentation/general/guides/authorization-guide/
- https://docs.github.com/en/rest

