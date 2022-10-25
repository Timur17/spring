package ru.otus.spring.dao;

import java.util.List;

public interface BookDao {
    int count();

    int insert(Book book);

    void updateById(Book book, int id);

    void deleteById(int id);

    Book getById(long id);

    List<Book> getAll();
}
