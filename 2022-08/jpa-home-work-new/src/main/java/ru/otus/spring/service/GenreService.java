package ru.otus.spring.service;


import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreService {

    void count();

    void insert(String genre);

    void updateById(String genre, int id);

    void deleteById(int id);

    void getAll();

    void getById(int id);

}
