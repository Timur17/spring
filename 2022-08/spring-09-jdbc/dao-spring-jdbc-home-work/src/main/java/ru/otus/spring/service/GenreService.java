package ru.otus.spring.service;


import ru.otus.spring.domain.BookGenre;

import java.util.List;

public interface GenreService {

    int count();

    int insert(String genre);

    void updateById(String genre, int id);

    void deleteById(int id);

    List<BookGenre> getAll();

    BookGenre getById(int id);

    public BookGenre getByIdAllBooks(int id);
}
