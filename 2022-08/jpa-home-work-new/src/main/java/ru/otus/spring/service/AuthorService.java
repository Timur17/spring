package ru.otus.spring.service;


import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorService {

    long count();

    void insert(String author);

    void updateById(String Author, int id);

    void deleteById(int id);

    void showAll();

    void showById(int id);
}
