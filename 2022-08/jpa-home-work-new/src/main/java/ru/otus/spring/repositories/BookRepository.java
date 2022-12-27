package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, Long> {

    Optional<Book> findByTitle(String title);

    Optional<Book> findById(String id);


    List<Book> findAllByAuthorAuthorBook(String authorBook);

    List<Book> findAllByGenreGenreBook(String genreBook);

    void deleteById(String id);

    void deleteAllByAuthorAuthorBook(String authorBook);

    void deleteAllByGenreGenreBook(String authorBook);
}
