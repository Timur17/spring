package ru.otus.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.domain.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("select a from Author a where a.authorBook = :authorBook")
    Optional<Author> findByAuthor(@Param("authorBook") String authorBook);
}
