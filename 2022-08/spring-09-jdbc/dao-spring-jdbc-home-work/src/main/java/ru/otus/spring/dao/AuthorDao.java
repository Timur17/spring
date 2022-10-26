package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookAuthor;

import java.util.List;

public interface AuthorDao {
    int count();

    int insert(BookAuthor bookAuthor);

    void updateById(BookAuthor bookAuthor, int id);

    void deleteById(int id);

    BookAuthor getById(long id);

    List<BookAuthor> getAll();
}
