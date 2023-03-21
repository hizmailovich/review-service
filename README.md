# ReviewMicroservice
This is a reactive part of simple "Movie App" that contains review microservice. In this app user can leave reviews for movies.

## Glossary
Review - information about review 

## Implemented pattern
### Service registry
The *service registry pattern* is a key part of service discovery. The registry is a database containing the network locations of service instances. A service registry needs to be highly available and up-to-date. One of the most popular implementations of this pattern is *Eureka Service Registry*.

## Apache Kafka
Reactive consumer is implemented as a part of Reactor Kafka. The consumer is receiving messages from the producer from other microservice (see [MovieMicroservice](https://github.com/hizmailovich/MovieMicroservice)). The main goal of this implementation is to simplify communication between microservices: if the movie is deleted, all reviews for this movie should be deleted too.
