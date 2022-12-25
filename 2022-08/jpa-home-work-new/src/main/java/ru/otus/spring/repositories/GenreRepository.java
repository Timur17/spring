package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.Genre;

import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, Long> {

    Optional<Genre> findByGenreBook(String genreBook);

}
