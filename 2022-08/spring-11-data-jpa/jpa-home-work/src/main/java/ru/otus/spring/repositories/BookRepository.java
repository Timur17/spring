package ru.otus.spring.repositories;

import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    long count();

    Book insert(Book book);

    void updateById(Book book, long id);

    void deleteById(long id);

    Optional<Book> getById(long id);

    Book getByTitle(String title);

    List<Book> getAll();


//    Book save(Book book);
//    Optional<Book> findById(long id);
//
//    List<Book> findAll();
//    List<Book> findByName(String title);
//
//    void updateNameById(long id, String title);
//    void deleteById(long id);
}
