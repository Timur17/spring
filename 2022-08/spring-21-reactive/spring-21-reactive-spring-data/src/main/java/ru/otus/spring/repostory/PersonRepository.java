package ru.otus.spring.repostory;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Person;

public interface PersonRepository extends ReactiveMongoRepository<Person, String> {

    // -ooo---ooo---ooo|---------
    // --------------------X-----
    Flux<Person> findByName(String name);

    @Query("{ 'name': ?0 }")
    Mono<Person> findFirstByName(String name);
}
