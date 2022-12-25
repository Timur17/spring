package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.Book;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, Long> {

    Optional<Book> findByTitle(String title);
}
