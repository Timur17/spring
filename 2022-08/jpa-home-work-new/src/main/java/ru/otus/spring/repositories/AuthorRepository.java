package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.Author;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, Long> {

    Optional<Author> findByAuthorBook(String authorBook);

}
