package ru.otus.spring.dao;

import ru.otus.spring.domain.BookGenre;

import java.util.List;

public interface GenreDao {
    int count();

    int insert(BookGenre bookGenre);

    void updateById(BookGenre bookGenre, int id);

    void deleteById(int id);

    BookGenre getById(long id);

    List<BookGenre> getAll();
}
