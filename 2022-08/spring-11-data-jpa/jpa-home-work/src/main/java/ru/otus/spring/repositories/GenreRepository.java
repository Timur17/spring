package ru.otus.spring.repositories;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreRepository {
    int count();

    int insert(Genre genre);

    void updateById(Genre genre, int id);

    void deleteById(int id);

    Genre getById(long id);

    Genre getByGenre(String genre);

    List<Genre> getAll();
}
