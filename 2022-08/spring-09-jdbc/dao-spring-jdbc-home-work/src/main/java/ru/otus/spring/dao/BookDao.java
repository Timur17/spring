package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookDao {
    int count();

    int insert(Book book);

    void updateById(Book book, int id);

    void deleteById(int id);

    Book getById(long id);

    List<Book> getByAuthor(String author);

    List<Book> getByGenre(String genre);

    List<Book> getAll();
}
